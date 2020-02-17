package com.zuhlke.upskilling.departureboard.seku.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.model.StationDetails


class StationListAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private var values: List<StationDetails>
) : ArrayAdapter<StationDetails>(context, layoutResource, values) {

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): StationDetails = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row: View = LayoutInflater.from(parent.context).inflate(R.layout.autocomplete_item, parent, false)
        val stationName = row.findViewById<TextView>(R.id.station_name)
        val stationCode = row.findViewById<TextView>(R.id.station_code)
        stationName.text = getItem(position).name
        stationCode.text = getItem(position).station_code
        return row
    }

    fun processList(stationDetails: List<StationDetails>) {
        values = stationDetails
        notifyDataSetChanged()
    }
}