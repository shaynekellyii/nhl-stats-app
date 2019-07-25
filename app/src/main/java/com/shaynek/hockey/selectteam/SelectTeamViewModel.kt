package com.shaynek.hockey.selectteam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.model.Resource
import com.shaynek.hockey.common.model.Team
import com.shaynek.hockey.common.util.addTo
import com.shaynek.hockey.common.util.scheduleForAndroid
import io.reactivex.disposables.CompositeDisposable

class SelectTeamViewModel(private val repository: AppRepository) : ViewModel() {

    val isTeamSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val teamsResource: MutableLiveData<Resource<List<Team>>> = MutableLiveData()
    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        repository.teamsFlowable
            .scheduleForAndroid()
            .subscribe(::onTeamsEmitted)
            .addTo(disposable)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onFavoriteTeamSelected(id: Int) {
        repository.setFavoriteTeam(id)
        isTeamSelected.postValue(true)
    }

    private fun onTeamsEmitted(resource: Resource<List<Team>>) = teamsResource.postValue(resource)
}
