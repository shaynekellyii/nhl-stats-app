package com.shaynek.hockey.standings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shaynek.hockey.R
import com.shaynek.hockey.common.BaseFragment
import com.shaynek.hockey.common.di.AppInjector
import com.shaynek.hockey.common.model.DataStatus
import kotlinx.android.synthetic.main.fragment_standings.*
import javax.inject.Inject

class StandingsFragment : BaseFragment() {

    @Inject
    lateinit var repository: StandingsRepository

    private val viewModel: StandingsViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { StandingsViewModel(repository) })
            .get(StandingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        initLiveDataObservers()
        return view
    }

    override fun onAttach(context: Context?) {
        AppInjector.injector.inject(this)
        super.onAttach(context)
    }

    private fun initLiveDataObservers() {
        viewModel.standings.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) standings_text.text = it.toString()
        })
        viewModel.dataStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                DataStatus.LOADING -> {
                    standings_progress_bar.visibility = View.VISIBLE
                }
                DataStatus.SUCCESS -> {
                    standings_progress_bar.visibility = View.GONE
                }
                DataStatus.FAILURE -> {
                    standings_progress_bar.visibility = View.GONE
                }
                null -> {
                }
            }
        })
    }
}