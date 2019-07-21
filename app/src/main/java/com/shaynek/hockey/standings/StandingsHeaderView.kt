package com.shaynek.hockey.standings

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.hockey.R
import kotlinx.android.synthetic.main.standings_header_view.view.*

class StandingsHeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val division: TextView? by lazy { findViewById<TextView>(R.id.standings_header_division) }

    fun setDivision(division: String) {
        this.division?.text = division
    }
}