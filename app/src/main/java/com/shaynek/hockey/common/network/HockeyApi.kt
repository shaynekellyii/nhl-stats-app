package com.shaynek.hockey.common.network

import com.shaynek.hockey.common.model.SeasonsResponse
import com.shaynek.hockey.common.model.StandingsResponse
import com.shaynek.hockey.common.model.TeamsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HockeyApi {
    @GET("/api/v1/teams")
    fun getTeams(
        @Query("expand") expand: String? = null,
        @Query("teamId") teamId: String? = null,
        @Query("stats") stats: String? = null
    ): Observable<TeamsResponse>

    @GET("api/v1/seasons/current")
    fun getCurrentSeason() : Observable<SeasonsResponse>

    @GET("api/v1/standings")
    fun getStandings(
        @Query("season") season: String? = null,
        @Query("date") date: String? = null,
        @Query("expand") expand: String? = null
    ): Observable<StandingsResponse>
}