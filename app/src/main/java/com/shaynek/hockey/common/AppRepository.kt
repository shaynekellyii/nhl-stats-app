package com.shaynek.hockey.common

import com.shaynek.hockey.common.db.HockeyDao
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.model.*
import com.shaynek.hockey.common.network.HockeyApi
import com.shaynek.hockey.common.util.forRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository(private val hockeyApi: HockeyApi, private val dao: HockeyDao, private val prefs: Preferences) {

    private val teamsApiFlowable: Flowable<Resource<List<Team>>> = hockeyApi.getTeams()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { it.teams }
        .filter { it.isNotEmpty() }
        .map { it.sortedBy { team -> team.name } }
        .doOnNext { dao.insertAllTeams(it).subscribeOn(Schedulers.io()) }
        .map<Resource<List<Team>>> { Resource.Success(it) }
        .onErrorReturn { Resource.Failure(it) }

    private val teamsDbFlowable: Flowable<Resource<List<Team>>> = dao.getAllTeams()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .filter { it.isNotEmpty() }
        .map { it.sortedBy { team -> team.name } }
        .map<Resource<List<Team>>> { Resource.Success(it) }
        .onErrorReturn { Resource.Failure(it) }

    private val initFlowable: Flowable<Resource<List<Team>>> = Flowable.just(Resource.Loading())

    val teamsFlowable: Flowable<Resource<List<Team>>> =
        Flowable.merge(initFlowable, teamsApiFlowable, teamsDbFlowable).forRepository()

    fun setFavoriteTeam(id: Int) = prefs.setFavoriteTeam(id)

    fun getCurrentSeason(): Observable<SeasonsResponse> = hockeyApi.getCurrentSeason()

    fun getStandings(season: String? = null): Observable<StandingsResponse> = hockeyApi.getStandings(season)

    fun getSchedule(date: String? = null): Observable<ScheduleResponse> =
        if (date != null) hockeyApi.getSchedule(date = date) else hockeyApi.getSchedule()
}