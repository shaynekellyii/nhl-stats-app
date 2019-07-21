package com.shaynek.hockey.schedule

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.hockey.R
import com.shaynek.hockey.common.model.Game
import com.shaynek.hockey.common.util.getLocalTime

class ScheduleItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val awayTeam: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_away_team) }
    private val homeTeam: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_home_team) }
    private val awayScore: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_away_score) }
    private val homeScore: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_home_score) }
    private val time: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_time) }
    private val state: TextView? by lazy { findViewById<TextView>(R.id.schedule_item_state) }

    fun setModel(game: Game) {
        val awayModel = game.teams.away
        val homeModel = game.teams.home
        awayTeam?.text = awayModel.team.name
        homeTeam?.text = homeModel.team.name
        awayScore?.text = awayModel.score.toString()
        homeScore?.text = homeModel.score.toString()
        time?.text = game.gameDate.getLocalTime()
        state?.text = game.status.detailedState
    }
}
