package com.shaynek.hockey.common.model

import androidx.room.Entity

@Entity(tableName = "franchises")
data class Franchise(
    val franchiseId: Int,
    val teamName: String,
    val link: String
)