package com.shaynek.hockey.common.model

data class ScheduleResponse(
    val copyright: String,
    val totalItems: Int,
    val totalEvents: Int,
    val totalGames: Int,
    val totalMatches: Int,
    val wait: Int,
    val dates: List<ScheduleDate>
)