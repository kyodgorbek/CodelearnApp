// CodeLearnApp.kt
package com.example.codelearnapp

import android.app.Application

import com.example.codelearnapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin



class CodeLearnApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CodeLearnApp)
            modules(appModule)
        }
    }
}