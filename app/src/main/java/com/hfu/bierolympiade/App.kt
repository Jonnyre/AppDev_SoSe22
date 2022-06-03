package com.hfu.bierolympiade

import android.app.Application
import com.hfu.bierolympiade.domain.*
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var addDemoEventsUseCase: AddDemoEventsUseCase

    @Inject
    lateinit var addDemoPlayerUseCase: AddDemoPlayerUseCase

    @Inject
    lateinit var addDemoGamesUseCase: AddDemoGamesUseCase

    @Inject
    lateinit var addDemoGameTypes: AddDemoGameTypes



    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        runBlocking {
            addDemoPlayerUseCase()
            addDemoEventsUseCase()
            addDemoGamesUseCase()
            addDemoGameTypes()
        }
    }
}