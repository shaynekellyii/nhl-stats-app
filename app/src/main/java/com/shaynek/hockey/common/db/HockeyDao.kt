package com.shaynek.hockey.common.db

import androidx.room.*
import com.shaynek.hockey.common.model.Team
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface HockeyDao {

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Maybe<List<Team>>

    @Query("SELECT * FROM teams WHERE id IN (:teamIds)")
    fun loadAllTeamsByIds(teamIds: IntArray): Flowable<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teams: List<Team>): Completable

    @Delete
    fun deleteTeam(team: Team)

    @Query("DELETE FROM teams")
    fun deleteAllTeams()
}