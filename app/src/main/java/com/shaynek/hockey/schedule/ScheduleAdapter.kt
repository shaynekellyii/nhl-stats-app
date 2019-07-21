package com.shaynek.hockey.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Game

private const val TITLE_VIEW_TYPE = 1
private const val SCHEDULE_VIEW_TYPE = 2

class ScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var games: List<Game> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_VIEW_TYPE -> TitleViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.standings_title_view, parent, false)
            )
            else -> ScheduleViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.schedule_item_view, parent, false) as ScheduleItemView
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ScheduleViewHolder) {
            holder.view.setModel(games[position - 1])
        } else {
            holder.itemView.findViewById<TextView>(R.id.section_title)?.text =
                holder.itemView.resources.getString(R.string.schedule)
        }
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) TITLE_VIEW_TYPE else SCHEDULE_VIEW_TYPE

    override fun getItemCount(): Int = games.size + 1

    private class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    private class ScheduleViewHolder(val view: ScheduleItemView) : RecyclerView.ViewHolder(view)
}