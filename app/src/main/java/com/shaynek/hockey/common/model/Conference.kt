package com.shaynek.hockey.common.model

import androidx.room.Entity

@Entity(tableName = "conferences")
data class Conference(
    val id: Int,
    val name: String,
    val link: String
)