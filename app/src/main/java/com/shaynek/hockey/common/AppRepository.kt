package com.shaynek.hockey.common

import com.shaynek.hockey.common.model.ScheduleResponse
import com.shaynek.hockey.common.model.SeasonsResponse
import com.shaynek.hockey.common.model.StandingsResponse
import com.shaynek.hockey.common.model.TeamsResponse
import com.shaynek.hockey.common.network.HockeyApi
import io.reactivex.Observable

class AppRepository(private val hockeyApi: HockeyApi) {

    // Cache team info since it's used in all parts of the app
    lateinit var teams: TeamsResponse

    fun getTeams(): Observable<TeamsResponse> =
        if (::teams.isInitialized) Observable.just(teams) else hockeyApi.getTeams()

    fun getCurrentSeason(): Observable<SeasonsResponse> = hockeyApi.getCurrentSeason()

    fun getStandings(season: String? = null): Observable<StandingsResponse> = hockeyApi.getStandings(season)

    fun getSchedule(date: String? = null): Observable<ScheduleResponse> =
        if (date != null) hockeyApi.getSchedule(date = date) else hockeyApi.getSchedule()
}