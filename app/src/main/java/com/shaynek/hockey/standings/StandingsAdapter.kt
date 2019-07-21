package com.shaynek.hockey.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Standings

private const val TITLE_VIEW_TYPE = 0
private const val HEADER_VIEW_TYPE = 1
private const val ENTRY_VIEW_TYPE = 2

class StandingsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<Standings> = emptyList()
        set(value) {
            field = value
            calculateHeaderPositions()
            notifyDataSetChanged()
        }
    private val headerPositions: LinkedHashSet<Int> = LinkedHashSet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_VIEW_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.standings_title_view, parent, false)
                TitleViewHolder(view)
            }
            HEADER_VIEW_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.standings_header_view, parent, false) as StandingsHeaderView
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.standings_entry_view, parent, false) as StandingsEntryView
                EntryViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.headerView.setDivision(data[headerPositions.indexOf(position)].division.name)
            }
            is EntryViewHolder -> {
                holder.entryView.setModel(data.flatMap { it.teamRecords }[position - getTeamOffset(position)])
            }
            else -> {
            }
        }
    }

    override fun getItemCount(): Int = data.sumBy { it.teamRecords.size } + data.size + 1

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TITLE_VIEW_TYPE
            headerPositions.contains(position) -> HEADER_VIEW_TYPE
            else -> ENTRY_VIEW_TYPE
        }
    }

    private fun calculateHeaderPositions() {
        headerPositions.clear()
        headerPositions.add(1)
        var seenTeams = 0
        for (i in 1 until data.size) {
            headerPositions.add(i + data[i].teamRecords.size + seenTeams + 1)
            seenTeams += data[i].teamRecords.size
        }
    }

    private fun getTeamOffset(position: Int): Int {
        var offset = 1
        headerPositions.forEach {
            if (it > position) return offset
            offset++
        }
        return offset
    }

    private class TitleViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    private class HeaderViewHolder(val headerView: StandingsHeaderView) : RecyclerView.ViewHolder(headerView)
    private class EntryViewHolder(val entryView: StandingsEntryView) : RecyclerView.ViewHolder(entryView)
}
