package com.crostage.trainchecker.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, RouteModule::class, StationModule::class,
    TrainModule::class, SeatModule::class, FavouriteModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

}

