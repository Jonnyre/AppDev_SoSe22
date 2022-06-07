package com.hfu.bierolympiade

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.hfu.bierolympiade.data.database.AppDatabase
import com.hfu.bierolympiade.data.database.event.EventDao
import com.hfu.bierolympiade.data.database.game.GameDao
import com.hfu.bierolympiade.data.database.gameType.GameTypeDao
import com.hfu.bierolympiade.data.database.match.MatchDao
import com.hfu.bierolympiade.data.database.matchparticipant.MatchParticipantDao
import com.hfu.bierolympiade.data.database.player.PlayerDao
import com.hfu.bierolympiade.data.database.player_event_crossref.PlayerEventCrossRefDao
import com.hfu.bierolympiade.data.database.team.TeamDao
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

    @Provides
    fun provideMatchDao(
        database: AppDatabase,
    ): MatchDao = database.matchDao()

    @Provides
    fun provideGameTypeDao(
        database: AppDatabase,
    ): GameTypeDao = database.gameTypeDao()

    @Provides
    fun providePlayerEventCrossRefDao(
        database: AppDatabase,
    ): PlayerEventCrossRefDao = database.playerEventCrossRefDao()

    @Provides
    fun provideMatchParticipantDao(
        database: AppDatabase,
    ): MatchParticipantDao = database.matchParticipantDao()

    @Provides
    fun provideTeamDao(
        database: AppDatabase,
    ): TeamDao = database.teamDao()
}