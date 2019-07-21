package com.shaynek.hockey.standings

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.TeamRecord

class StandingsEntryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val team: TextView? by lazy { findViewById<TextView>(R.id.standings_entry_team) }
    private val wins: TextView? by lazy { findViewById<TextView>(R.id.standings_entry_wins) }
    private val losses: TextView? by lazy { findViewById<TextView>(R.id.standings_entry_losses) }
    private val otLosses: TextView? by lazy { findViewById<TextView>(R.id.standings_entry_ot) }
    private val pts: TextView? by lazy { findViewById<TextView>(R.id.standings_entry_pts) }

    fun setModel(record: TeamRecord) {
        team?.text = record.team.name
        wins?.text = record.leagueRecord.wins.toString()
        losses?.text = record.leagueRecord.losses.toString()
        otLosses?.text = record.leagueRecord.ot.toString()
        pts?.text = record.points.toString()
    }
}