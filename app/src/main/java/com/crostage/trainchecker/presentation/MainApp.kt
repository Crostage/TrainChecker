package com.crostage.trainchecker.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.crostage.trainchecker.di.AppComponent
import com.crostage.trainchecker.di.DaggerAppComponent

class MainApp : Application() {

    companion object {
        private var instance: MainApp? = null
        fun connectionType() = instance?.connectionType()
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().context(this).build()
    }

    fun connectionType(): Int {
        var connectionType = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            val capabilities =
                cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        connectionType = 2
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        connectionType = 1
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                        connectionType = 3
                    }
                }
            }

        }
        return connectionType
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

