package com.shaynek.hockey.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.model.DataStatus
import com.shaynek.hockey.common.model.ScheduleResponse
import io.reactivex.disposables.CompositeDisposable

class ScheduleViewModel(private val repository: AppRepository) : ViewModel() {

    val dataStatus = MutableLiveData(DataStatus.LOADING)
    val schedule = MutableLiveData<ScheduleResponse>()
    private val disposable = CompositeDisposable()

    init {
        repository.getSchedule().subscribe(::onScheduleRetrieved, ::onScheduleApiError)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun onScheduleRetrieved(schedule: ScheduleResponse?) {
        schedule?.let { this.schedule.postValue(it) }
        dataStatus.postValue(DataStatus.SUCCESS)
    }

    private fun onScheduleApiError(e: Throwable) {
        dataStatus.postValue(DataStatus.FAILURE)
        e.printStackTrace()
    }
}