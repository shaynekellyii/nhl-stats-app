package com.shaynek.hockey.common.model

data class Team(
    val id: Int,
    val name: String,
    val venue: Venue,
    val abbreviation: String,
    val teamName: String,
    val locationName: String,
    val firstYearOfPlay: String,
    val division: Division,
    val conference: Conference,
    val franchise: Franchise,
    val shortName: String,
    val officialSiteUrl: String,
    val franchiseId: Int,
    val active: Boolean
)