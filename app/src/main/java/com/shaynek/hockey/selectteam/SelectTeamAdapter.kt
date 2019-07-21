package com.shaynek.hockey.selectteam

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Team

class SelectTeamAdapter(private val viewModel: SelectTeamViewModel) :
    ListAdapter<Team, SelectTeamAdapter.ViewHolder>(TeamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val frameLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.select_team_list_item, parent, false) as FrameLayout
        return ViewHolder(frameLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.frameLayout) {
            val team = getItem(position)
            findViewById<TextView>(R.id.team_item_view_text).text = team.name
            setOnClickListener { viewModel.onFavoriteTeamSelected(team.id) }
        }
    }

    class ViewHolder(val frameLayout: FrameLayout) : RecyclerView.ViewHolder(frameLayout)

    class TeamDiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean = oldItem == newItem
    }
}