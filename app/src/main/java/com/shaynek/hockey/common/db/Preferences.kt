package com.shaynek.hockey.common.db

import android.content.SharedPreferences

private const val FAVORITE_TEAM_KEY = "favorite_team"
const val NO_ID_SET = -1

class Preferences(private val sharedPrefs: SharedPreferences) {

    fun setFavoriteTeam(id: Int) {
        sharedPrefs.edit().putInt(FAVORITE_TEAM_KEY, id).apply()
    }

    fun getFavoriteTeam(): Int = sharedPrefs.getInt(FAVORITE_TEAM_KEY, NO_ID_SET)
}