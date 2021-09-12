package com.crostage.trainchecker.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import com.crostage.trainchecker.di.AppComponent
import com.crostage.trainchecker.di.DaggerAppComponent
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()

        detectUndeliverableException()
    }

    private fun detectUndeliverableException() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                e.message?.let { Log.e("UndeliverableException", it) }
            } else {
                Thread.currentThread()
                    .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
            }
        }
    }

}


val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

