package com.example.chatbot

import android.app.Application
import com.example.chatbot.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ChatBotApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // TIMBER FOR LOGGING
        Timber.plant(Timber.DebugTree())

        //KOIN FOR DI
        startKoin {
            androidLogger()
            // Android context
            androidContext(this@ChatBotApp)
            // modules
            modules(listOf(vmModule))
        }
    }
}