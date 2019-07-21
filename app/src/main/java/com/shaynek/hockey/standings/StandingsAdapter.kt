package com.shaynek.hockey.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Standings

private const val HEADER_VIEW_TYPE = 1
private const val TEAM_VIEW_TYPE = 2

class StandingsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<Standings> = emptyList()
        set(value) {
            field = value
            calculateHeaderPositions()
            notifyDataSetChanged()
        }
    val headerPositions: LinkedHashSet<Int> = LinkedHashSet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.standings_header_view, parent, false)
                        as StandingsHeaderView
                HeaderViewHolder(view)
            }
            else -> TeamViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.headerView.setDivision(data[headerPositions.indexOf(position)].division.name)
            }
            is TeamViewHolder -> {

            }
            else -> {
            }
        }
    }

    override fun getItemCount(): Int = data.sumBy { it.teamRecords.size } + data.size

    override fun getItemViewType(position: Int): Int =
        if (headerPositions.contains(position)) HEADER_VIEW_TYPE else TEAM_VIEW_TYPE

    private fun calculateHeaderPositions() {
        headerPositions.clear()
        headerPositions.add(0)
        var seenTeams = 0
        for (i in 1 until data.size) {
            headerPositions.add(i + data[i].teamRecords.size + seenTeams)
            seenTeams += data[i].teamRecords.size
        }
    }

    class HeaderViewHolder(val headerView: StandingsHeaderView) : RecyclerView.ViewHolder(headerView)
    class TeamViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
