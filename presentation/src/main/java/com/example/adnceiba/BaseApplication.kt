package com.example.adnceiba

import android.app.Application
import com.example.adnceiba.di.presentationModule
import com.example.data.di.dataModule
import com.example.database.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    databaseModule,
                    dataModule,
                    presentationModule
                )
            )
        }
    }
}