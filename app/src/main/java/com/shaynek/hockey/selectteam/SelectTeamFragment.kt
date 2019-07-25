package com.shaynek.hockey.selectteam

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.hockey.BaseFragment
import com.shaynek.hockey.R
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.db.HockeyDao
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.di.AppInjector
import com.shaynek.hockey.common.model.DataStatus
import com.shaynek.hockey.common.model.Resource
import kotlinx.android.synthetic.main.fragment_select_team.*
import javax.inject.Inject

class SelectTeamFragment : BaseFragment() {

    @Inject
    lateinit var repository: AppRepository
    @Inject
    lateinit var prefs: Preferences

    private val viewModel: SelectTeamViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { SelectTeamViewModel(repository) })
            .get(SelectTeamViewModel::class.java)
    }
    private val adapter by lazy { SelectTeamAdapter(viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_team, container, false)

        with(view.findViewById<RecyclerView>(R.id.select_team_recycler_view)) {
            adapter = this@SelectTeamFragment.adapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }

        initLiveDataObservers()
        return view
    }

    override fun onAttach(context: Context?) {
        AppInjector.injector.inject(this)
        super.onAttach(context)
    }

    private fun initLiveDataObservers() {
        viewModel.teamsResource.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    select_team_progress_bar.visibility = View.GONE
                    select_team_recycler_view.visibility = View.VISIBLE
                    select_team_error_text.visibility = View.GONE
                    adapter.submitList(it.data)
                }
                is Resource.Failure -> {
                    select_team_progress_bar.visibility = View.GONE
                    select_team_recycler_view.visibility = View.GONE
                    select_team_error_text.visibility = View.VISIBLE
                }
                is Resource.Loading -> {
                    select_team_progress_bar.visibility = View.VISIBLE
                    select_team_recycler_view.visibility = View.GONE
                    select_team_error_text.visibility = View.GONE
                }
            }
        })
        viewModel.isTeamSelected.observe(viewLifecycleOwner, Observer {
            if (it) findNavController().navigate(R.id.action_selectTeamFragment_to_scheduleFragment)
        })
    }
}