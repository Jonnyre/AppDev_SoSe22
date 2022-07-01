package com.hfu.bierolympiade.data.database.gameType

import com.hfu.bierolympiade.domain.model.GameType
import com.hfu.bierolympiade.domain.model.GameTypeId

fun gameTypeToDb(gameType: GameType): GameTypeDb = GameTypeDb(
    gameTypeId = gameType.gameTypeId.value,
    name = gameType.name,
    icon= gameType.icon,
    rules = gameType.rules,
    isHighScore = gameType.isHighScore,
    isWinnerHighest = gameType.isWinnerHighest
)

fun gameTypeFromDb(gameTypeDb: GameTypeDb): GameType {
    return GameType.create(
        gameTypeId = GameTypeId(gameTypeDb.gameTypeId),
        name = gameTypeDb.name,
        icon = gameTypeDb.icon,
        rules = gameTypeDb.rules,
        isHighScore = gameTypeDb.isHighScore,
        isWinnerHighest = gameTypeDb.isWinnerHighest
    )
}