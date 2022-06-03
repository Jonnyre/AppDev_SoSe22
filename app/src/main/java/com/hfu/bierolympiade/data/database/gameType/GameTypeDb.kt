package com.hfu.bierolympiade.data.database.gameType

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "gameType")
data class GameTypeDb(
    @PrimaryKey
    val gameTypeId: String,
    val name: String,
    val icon: String,
    val rules: String,
)