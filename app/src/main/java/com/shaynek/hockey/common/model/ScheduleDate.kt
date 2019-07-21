package com.shaynek.hockey.common.model

data class ScheduleDate(
    val date: String,
    val totalItems: Int,
    val totalEvents: Int,
    val totalGames: Int,
    val totalMatches: Int,
    val games: List<Game>
)