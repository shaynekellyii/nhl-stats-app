package com.shaynek.hockey.common.model

import java.util.*

data class TeamRecord(
    val team: Team,
    val leagueRecord: LeagueRecord,
    val goalsAgainst: Int,
    val goalsScored: Int,
    val points: Int,
    val divisionRank: String,
    val divisionL10Rank: String,
    val divisionRoadRank: String,
    val divisionHomeRank: String,
    val conferenceRank: String,
    val conferenceL10Rank: String,
    val conferenceRoadRank: String,
    val conferenceHomeRank: String,
    val leagueRank: String,
    val leagueL10Rank: String,
    val leagueRoadRank: String,
    val leagueHomeRank: String,
    val wildCardRank: String,
    val row: String,
    val gamesPlayed: String,
    val streak: Streak,
    val clinchIndicator: String,
    val lastUpdate: Date
)