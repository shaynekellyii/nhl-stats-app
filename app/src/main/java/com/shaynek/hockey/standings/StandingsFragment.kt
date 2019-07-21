package com.shaynek.hockey.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shaynek.hockey.R
import com.shaynek.hockey.common.BaseFragment

class StandingsFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        return view
    }
}