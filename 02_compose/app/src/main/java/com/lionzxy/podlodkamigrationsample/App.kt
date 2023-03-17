package com.lionzxy.podlodkamigrationsample

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.lionzxy.podlodkamigrationsample.utils.LocalCiceroneHolder

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val ciceroneHolder = LocalCiceroneHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}