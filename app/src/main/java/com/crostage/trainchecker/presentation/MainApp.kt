package com.crostage.trainchecker.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.crostage.trainchecker.di.AppComponent
import com.crostage.trainchecker.di.DaggerAppComponent

class MainApp : Application() {

    companion object {
        private var instance: MainApp? = null
        fun hasNetwork() = instance?.isNetworkConnected()
    }


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().context(this).build()
    }

//todo заменить деприкейтед

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

