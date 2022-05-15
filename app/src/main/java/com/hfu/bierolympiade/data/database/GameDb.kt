package com.hfu.bierolympiade.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime


@Entity(tableName = "game")
data class GameDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)