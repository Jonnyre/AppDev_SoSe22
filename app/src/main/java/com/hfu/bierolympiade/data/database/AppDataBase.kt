package com.hfu.bierolympiade.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 2,
    entities = [
        EventDb::class,
        PlayerDb::class,
        GameDb::class
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun playerDao(): PlayerDao
    abstract fun gameDao(): GameDao
}