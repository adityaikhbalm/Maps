package com.adityaikhbalm.maps

import android.app.Application
import com.adityaikhbalm.maps.di.mapModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(mapModule)
        }
    }
}