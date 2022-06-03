package com.hfu.bierolympiade.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hfu.bierolympiade.data.database.event.EventDao
import com.hfu.bierolympiade.data.database.event.EventDb
import com.hfu.bierolympiade.data.database.game.GameDao
import com.hfu.bierolympiade.data.database.game.GameDb
import com.hfu.bierolympiade.data.database.gameType.GameTypeDao
import com.hfu.bierolympiade.data.database.gameType.GameTypeDb
import com.hfu.bierolympiade.data.database.match.MatchDao
import com.hfu.bierolympiade.data.database.match.MatchDb
import com.hfu.bierolympiade.data.database.player.PlayerDao
import com.hfu.bierolympiade.data.database.player.PlayerDb
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDb

@Database(
    version = 12,
    entities = [
        EventDb::class,
        PlayerDb::class,
        GameDb::class,
        MatchDb::class,
        PlayerEventCrossRefDb::class,
        GameTypeDb::class
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun playerDao(): PlayerDao
    abstract fun gameDao(): GameDao
    abstract fun matchDao(): MatchDao
    abstract fun gameTypeDao(): GameTypeDao
}