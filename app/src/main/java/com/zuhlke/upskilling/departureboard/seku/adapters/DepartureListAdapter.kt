package com.zuhlke.upskilling.departureboard.seku.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.model.All
import com.zuhlke.upskilling.departureboard.seku.model.Departures


class DepartureListAdapter(private var departureList: List<All> = emptyList()) :
    RecyclerView.Adapter<DepartureListAdapter.DepartureViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureViewHolder {
        val mItemView: View = LayoutInflater.from(parent.context).inflate(R.layout.departurelist_item, parent, false)
        return DepartureViewHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return departureList.size
    }

    override fun onBindViewHolder(holder: DepartureViewHolder, position: Int) {
        holder.bind(departureList[position])
    }

    fun processList(departures: Departures) {
        departureList = departures.all
        notifyDataSetChanged()
    }

    class DepartureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val aimedDepartureTimeItemView: TextView = itemView.findViewById(R.id.departureItemWord)
        private val platformItemView: TextView = itemView.findViewById(R.id.platform)
        fun bind(all: All){
            aimedDepartureTimeItemView.text = all.aimed_departure_time
            platformItemView.text = all.platform.toString()
        }
    }
}