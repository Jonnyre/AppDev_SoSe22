package com.hfu.bierolympiade

import android.content.Context
import androidx.room.Room
import com.hfu.bierolympiade.data.database.AppDatabase
import com.hfu.bierolympiade.data.database.EventDao
import com.hfu.bierolympiade.data.database.GameDao
import com.hfu.bierolympiade.data.database.PlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "app")
        .apply {
            if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
        }
        .build()

    @Provides
    fun provideEventsDao(
        database: AppDatabase,
    ): EventDao = database.eventDao()

    @Provides
    fun provideGamesDao(
        database: AppDatabase,
    ): GameDao = database.gameDao()

    @Provides
    fun providePlayersDao(
        database: AppDatabase,
    ): PlayerDao = database.playerDao()
}