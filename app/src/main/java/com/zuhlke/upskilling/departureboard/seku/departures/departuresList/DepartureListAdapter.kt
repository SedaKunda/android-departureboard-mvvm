package com.zuhlke.upskilling.departureboard.seku.departures.departuresList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.network.model.All
import com.zuhlke.upskilling.departureboard.seku.network.model.Departures


class DepartureListAdapter(private var departureList: List<All> = emptyList(), private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<DepartureListAdapter.DepartureViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureViewHolder {
        val mItemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_departure_list, parent, false)
        return DepartureViewHolder(
            mItemView
        )
    }

    override fun getItemCount(): Int {
        return departureList.size
    }

    override fun onBindViewHolder(holder: DepartureViewHolder, position: Int) {
        holder.bind(departureList[position], itemClickListener)
    }

    fun processList(departures: Departures) {
        departureList = departures.all
        notifyDataSetChanged()
    }

    inner class DepartureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val aimedDepartureTimeItemView: TextView = itemView.findViewById(R.id.departureItemWord)
        private val platformItemView: TextView = itemView.findViewById(R.id.platform)

        fun bind(all: All, clickListener: OnItemClickListener){
            aimedDepartureTimeItemView.text = all.aimed_departure_time
            platformItemView.text = all.platform.toString()

            itemView.setOnClickListener {
                clickListener.onItemClicked(all)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(all: All)
}

//typealias OnItemClickListener2 = (All) -> Unit //same functionality as above interface