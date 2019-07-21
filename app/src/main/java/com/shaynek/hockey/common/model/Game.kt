package com.shaynek.hockey.common.model

import java.util.*

data class Game(
    val gamePk: Int,
    val link: String,
    val gameType: String,
    val season: String,
    val gameDate: Date,
    val status: GameStatus,
    val teams: GameTeams,
    val venue: Venue,
    val content: GameContent
)