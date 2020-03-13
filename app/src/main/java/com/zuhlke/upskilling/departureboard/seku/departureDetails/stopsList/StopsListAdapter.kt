package com.zuhlke.upskilling.departureboard.seku.departureDetails.stopsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuhlke.upskilling.departureboard.seku.R
import com.zuhlke.upskilling.departureboard.seku.network.model.ServiceTimetableDetails
import com.zuhlke.upskilling.departureboard.seku.network.model.Stops

class StopsListAdapter (private var stopsList: List<Stops> = emptyList()) :
    RecyclerView.Adapter<StopsListAdapter.StopsViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopsViewHolder {
        val mItemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_stops_list, parent, false)
        return StopsViewHolder(
            mItemView
        )
    }

    override fun getItemCount(): Int {
        return stopsList.size
    }

    override fun onBindViewHolder(holder: StopsViewHolder, position: Int) {
        val data = stopsList[position]
        holder.bind(data)
        holder.itemView.contentDescription = "${data.station_name} from platform ${data.platform} at ${data.aimed_departure_time}"
    }

    fun processList(timetable: ServiceTimetableDetails) {
        stopsList = timetable.stops
        notifyDataSetChanged()
    }

    class StopsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stationNameView: TextView = itemView.findViewById(R.id.stationName)
        private val platformValueView: TextView = itemView.findViewById(R.id.platformValue)
        private val departureTimeView: TextView = itemView.findViewById(R.id.departureTime)

        fun bind(stops: Stops){
            stationNameView.text = stops.station_name
            platformValueView.text = stops.platform
            departureTimeView.text = stops.aimed_departure_time
        }
    }
}