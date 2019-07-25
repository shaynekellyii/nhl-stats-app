package com.shaynek.hockey.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Game

class ScheduleAdapter : RecyclerView.Adapter<ScheduleViewHolder>() {

    var games: List<Game> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.schedule_item_view, parent, false) as ScheduleItemView
        )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.view.setModel(games[position])
    }

    override fun getItemCount(): Int = games.size
}

class ScheduleViewHolder(val view: ScheduleItemView) : RecyclerView.ViewHolder(view)