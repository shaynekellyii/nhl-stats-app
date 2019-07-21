package com.shaynek.hockey.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shaynek.hockey.BaseFragment
import com.shaynek.hockey.R
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.di.AppInjector
import com.shaynek.hockey.common.model.DataStatus
import kotlinx.android.synthetic.main.fragment_schedule.*
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {

    @Inject
    lateinit var repository: AppRepository

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { ScheduleViewModel(repository) })
            .get(ScheduleViewModel::class.java)
    }

//    private val adapter by lazy { StandingsAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        initObservers()
        return view
    }

    override fun onAttach(context: Context?) {
        AppInjector.injector.inject(this)
        super.onAttach(context)
    }

    private fun initObservers() {
        viewModel.schedule.observe(viewLifecycleOwner, Observer {
            it?.let { schedule_error_text.text = it.toString() }
        })
        viewModel.dataStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                DataStatus.LOADING -> {
                    schedule_progress_bar.visibility = View.VISIBLE
                }
                DataStatus.SUCCESS -> {
                    schedule_progress_bar.visibility = View.GONE
                }
                DataStatus.FAILURE -> {
                    schedule_progress_bar.visibility = View.GONE
                }
                null -> {}
            }
        })
    }
}