package com.shaynek.hockey.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaynek.hockey.common.model.Team

@Database(entities = [Team::class], version = 1)
abstract class HockeyDb : RoomDatabase() {
    abstract fun teamDao(): HockeyDao
}