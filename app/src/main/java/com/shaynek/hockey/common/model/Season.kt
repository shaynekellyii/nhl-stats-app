package com.shaynek.hockey.common.model

data class Season(
    val seasonId: String,
    val regularSeasonStartDate: String,
    val regularSeasonEndDate: String,
    val seasonEndDate: String,
    val numberOfGames: Int,
    val tiesInUse: Boolean,
    val olympicsParticipation: Boolean,
    val conferencesInUse: Boolean,
    val divisionsInUse: Boolean,
    val wildCardInUse: Boolean
)