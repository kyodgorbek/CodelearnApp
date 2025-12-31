// CodeLearnApp.kt
package com.example.codelearnapp

import android.app.Application

import com.example.codelearnapp.data.local.AppDatabase
import com.example.codelearnapp.data.local.DatabaseInitializer
import com.example.codelearnapp.di.appModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level



class CodeLearnApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CodeLearnApp)
            modules(appModule)
        }

        // Initialize database
        val database = get<AppDatabase>()
        GlobalScope.launch {
            DatabaseInitializer.initializeDatabase(database)
        }
    }
}