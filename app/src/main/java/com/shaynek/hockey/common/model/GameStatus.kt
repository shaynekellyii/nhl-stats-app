package com.shaynek.hockey.common.model

data class GameStatus(
    val abstractGameState: String,
    val codedGameState: String,
    val detailedState: String,
    val statusCode: String,
    val startTimeTBD: Boolean
)