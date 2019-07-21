package com.shaynek.hockey.selectteam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.model.DataStatus
import com.shaynek.hockey.common.model.Team
import com.shaynek.hockey.common.model.TeamsResponse
import io.reactivex.disposables.CompositeDisposable

class SelectTeamViewModel(
    private val repository: SelectTeamRepository,
    private val prefs: Preferences
) : ViewModel() {

    val teams: MutableLiveData<List<Team>> = MutableLiveData()
    val dataStatus: MutableLiveData<DataStatus> = MutableLiveData(DataStatus.LOADING)
    val isTeamSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable.add(
            repository.getTeams().subscribe(::onTeamsLoaded, ::onTeamsApiError)
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onFavoriteTeamSelected(id: Int) {
        prefs.setFavoriteTeam(id)
        isTeamSelected.postValue(true)
    }

    private fun onTeamsLoaded(response: TeamsResponse?) {
        response?.let { teams.postValue(it.teams.sortedBy { team -> team.name }) }
        dataStatus.postValue(DataStatus.SUCCESS)
    }

    private fun onTeamsApiError(e: Throwable) {
        e.printStackTrace()
        dataStatus.postValue(DataStatus.FAILURE)
    }
}