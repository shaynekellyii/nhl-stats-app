package com.shaynek.hockey.common.model

import androidx.room.Entity

@Entity(tableName = "divisions")
data class Division(
    val id: Int,
    val name: String,
    val nameShort: String,
    val link: String,
    val abbreviation: String
)