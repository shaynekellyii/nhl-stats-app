package com.shaynek.hockey.standings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.R
import com.shaynek.hockey.BaseFragment
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.di.AppInjector
import com.shaynek.hockey.common.model.DataStatus
import kotlinx.android.synthetic.main.fragment_standings.*
import javax.inject.Inject

class StandingsFragment : BaseFragment() {

    @Inject
    lateinit var repository: AppRepository

    private val viewModel: StandingsViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { StandingsViewModel(repository) })
            .get(StandingsViewModel::class.java)
    }
    private val adapter by lazy { StandingsAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)

        with(view.findViewById<RecyclerView>(R.id.standings_recycler_view)) {
            adapter = this@StandingsFragment.adapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        initLiveDataObservers()
        return view
    }

    override fun onAttach(context: Context?) {
        AppInjector.injector.inject(this)
        super.onAttach(context)
    }

    private fun initLiveDataObservers() {
        viewModel.standings.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.divisions = it
        })
        viewModel.dataStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                DataStatus.LOADING -> {
                    standings_progress_bar.visibility = View.VISIBLE
                    standings_error_text.visibility = View.GONE
                }
                DataStatus.SUCCESS -> {
                    standings_progress_bar.visibility = View.GONE
                    standings_error_text.visibility = View.GONE
                }
                DataStatus.FAILURE -> {
                    standings_progress_bar.visibility = View.GONE
                    standings_error_text.visibility = View.VISIBLE
                }
                null -> {
                }
            }
        })
    }
}