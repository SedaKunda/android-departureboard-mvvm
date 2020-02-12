package com.zuhlke.upskilling.departureboard.seku.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.model.All
import com.zuhlke.upskilling.departureboard.seku.model.Departures

class DepartureListAdapter(context: Context, private var departureList: List<All> = emptyList()) :
    RecyclerView.Adapter<DepartureListAdapter.DepartureViewHolder?>() {
    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartureViewHolder {
        val mItemView: View = mInflater.inflate(R.layout.departurelist_item, parent, false)
        return DepartureViewHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return departureList.size
    }

    override fun onBindViewHolder(holder: DepartureViewHolder, position: Int) {
        val mCurrent: String = departureList[position].aimed_departure_time
        holder.departureItemView.text = mCurrent
    }

    fun processList(departures: Departures) {
        departureList = departures.all
        notifyDataSetChanged()
    }

    class DepartureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departureItemView: TextView = itemView.findViewById(R.id.departureItemWord)
    }
}