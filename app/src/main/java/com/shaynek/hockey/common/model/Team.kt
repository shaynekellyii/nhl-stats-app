package com.shaynek.hockey.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shaynek.hockey.common.db.Converters

@Entity(tableName = "teams")
@TypeConverters(Converters::class)
data class Team(
    @PrimaryKey val id: Int,
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