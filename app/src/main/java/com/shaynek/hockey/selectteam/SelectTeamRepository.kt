package com.shaynek.hockey.selectteam

import com.shaynek.hockey.common.model.TeamsResponse
import com.shaynek.hockey.common.network.HockeyApi
import io.reactivex.Observable

class SelectTeamRepository(private val hockeyApi: HockeyApi) {
    fun getTeams(): Observable<TeamsResponse> = hockeyApi.getTeams()
}