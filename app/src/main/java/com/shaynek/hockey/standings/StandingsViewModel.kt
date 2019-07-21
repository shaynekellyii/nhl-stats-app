package com.shaynek.hockey.standings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.model.DataStatus
import com.shaynek.hockey.common.model.Standings
import com.shaynek.hockey.common.model.StandingsResponse
import io.reactivex.disposables.CompositeDisposable

class StandingsViewModel(private val repository: AppRepository) : ViewModel() {

    val standings: MutableLiveData<List<Standings>> = MutableLiveData()
    val dataStatus: MutableLiveData<DataStatus> = MutableLiveData(DataStatus.LOADING)
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        disposable.add(repository
            .getCurrentSeason()
            .flatMap { repository.getStandings(it.seasons.firstOrNull()?.seasonId) }
            .subscribe(::onStandingsLoaded, ::onStandingsError))
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun onStandingsLoaded(response: StandingsResponse?) {
        response?.let { standings.postValue(it.records) }
        dataStatus.postValue(DataStatus.SUCCESS)
    }

    private fun onStandingsError(e: Throwable) {
        e.printStackTrace()
        dataStatus.postValue(DataStatus.FAILURE)
    }
}