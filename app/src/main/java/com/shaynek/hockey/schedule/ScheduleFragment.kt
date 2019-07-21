package com.shaynek.hockey.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private val adapter by lazy { ScheduleAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        with(view.findViewById<RecyclerView>(R.id.schedule_recycler_view)) {
            adapter = this@ScheduleFragment.adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
            setHasFixedSize(true)
        }
        initObservers()
        return view
    }

    override fun onAttach(context: Context?) {
        AppInjector.injector.inject(this)
        super.onAttach(context)
    }

    private fun initObservers() {
        viewModel.schedule.observe(viewLifecycleOwner, Observer { response ->
            response?.let { adapter.games = it.dates.flatMap { date -> date.games } }
        })
        viewModel.dataStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                DataStatus.LOADING -> {
                    schedule_progress_bar.visibility = View.VISIBLE
                    schedule_error_text.visibility = View.GONE
                }
                DataStatus.SUCCESS -> {
                    schedule_progress_bar.visibility = View.GONE
                    schedule_error_text.visibility = View.GONE
                }
                DataStatus.FAILURE -> {
                    schedule_progress_bar.visibility = View.GONE
                    schedule_error_text.visibility = View.VISIBLE
                }
                else -> {
                }
            }
        })
    }
}