package com.hfu.bierolympiade.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime


@Entity(tableName = "player")
data class PlayerDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
)