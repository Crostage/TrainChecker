package com.crostage.trainchecker.presentation

import android.app.Application
import android.content.Context
import com.crostage.trainchecker.di.AppComponent
import com.crostage.trainchecker.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }