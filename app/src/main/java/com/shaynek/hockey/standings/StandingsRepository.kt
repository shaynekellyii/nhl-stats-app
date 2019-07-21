package com.shaynek.hockey.standings

import com.shaynek.hockey.common.model.SeasonsResponse
import com.shaynek.hockey.common.model.StandingsResponse
import com.shaynek.hockey.common.network.HockeyApi
import io.reactivex.Observable

class StandingsRepository(private val hockeyApi: HockeyApi) {
    fun getCurrentSeason(): Observable<SeasonsResponse> = hockeyApi.getCurrentSeason()
    fun getStandings(season: String? = null): Observable<StandingsResponse> = hockeyApi.getStandings(season)
}