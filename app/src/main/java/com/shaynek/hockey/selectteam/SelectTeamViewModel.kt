package com.shaynek.hockey.selectteam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.db.HockeyDao
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.model.DataStatus
import com.shaynek.hockey.common.model.Team
import com.shaynek.hockey.common.model.TeamsResponse
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SelectTeamViewModel(
    private val repository: AppRepository,
    private val dao: HockeyDao,
    private val prefs: Preferences
) : ViewModel() {

    val teams: MutableLiveData<List<Team>> = MutableLiveData()
    val dataStatus: MutableLiveData<DataStatus> = MutableLiveData(DataStatus.LOADING)
    val isTeamSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable.add(
            dao.getAllTeams()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter { it.isNotEmpty() }
                .switchIfEmpty { fetchTeamsFromServer() }
                .subscribe(
                    { teams -> onTeamsLoaded(teams, true) },
                    { fetchTeamsFromServer() }, ::fetchTeamsFromServer
                )
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

    private fun onTeamsLoaded(loadedTeams: List<Team>, isFromDb: Boolean) {
        teams.postValue(loadedTeams.sortedBy { team -> team.name })
        dataStatus.postValue(DataStatus.SUCCESS)
        if (!isFromDb) {
            disposable.add(
                dao.insertAllTeams(loadedTeams)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe { }
            )
        }
    }

    private fun fetchTeamsFromServer(): Disposable {
        return repository.getTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { t -> t.teams }
            .doOnError(::onTeamsApiError)
            .subscribe { teams -> onTeamsLoaded(teams, false) }
    }

    private fun onTeamsApiError(e: Throwable) {
        e.printStackTrace()
        dataStatus.postValue(DataStatus.FAILURE)
    }
}