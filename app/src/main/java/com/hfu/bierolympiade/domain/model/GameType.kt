package com.hfu.bierolympiade.domain.model

@JvmInline
value class GameTypeId(val value: String)


class GameType private constructor(
    val gameTypeId: GameTypeId,
    val name: String,
    val icon: String,
    val rules: String,
    val isHighScore: Boolean,
    val isWinnerHighest: Boolean,
) {
    companion object {
        fun create(
            gameTypeId: GameTypeId,
            name: String,
            icon: String,
            rules: String,
            isHighScore: Boolean,
            isWinnerHighest: Boolean,
        ): GameType {
            return GameType(gameTypeId, name, icon, rules, isHighScore, isWinnerHighest)
        }
    }
}