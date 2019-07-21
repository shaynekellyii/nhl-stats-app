package com.shaynek.hockey.common.model

data class Standings(
    val standingsType: String,
    val league: League,
    val division: Division,
    val conference: Conference,
    val season: String,
    val teamRecords: List<TeamRecord>)