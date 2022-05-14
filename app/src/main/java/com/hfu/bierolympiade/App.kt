package com.hfu.bierolympiade

import android.app.Application
import androidx.room.Room
import com.hfu.bierolympiade.data.database.AppDatabase
import com.hfu.bierolympiade.data.eventRepo
import com.hfu.bierolympiade.data.playerRepo
import com.hfu.bierolympiade.domain.AddDemoEventsUseCase
import com.hfu.bierolympiade.domain.AddDemoPlayerUseCase
import kotlinx.coroutines.runBlocking

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "app")
            .apply {
                if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
            }
            .build()

        runBlocking {
            AddDemoEventsUseCase(eventRepo)()
            AddDemoPlayerUseCase(playerRepo)()
        }
    }

    companion object {
        lateinit var database: AppDatabase
    }
}