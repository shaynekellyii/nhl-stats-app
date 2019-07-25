package com.shaynek.hockey.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venues")
data class Venue(
    @PrimaryKey val name: String,
    val link: String,
    val city: String
)