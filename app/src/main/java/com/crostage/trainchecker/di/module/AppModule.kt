package com.crostage.trainchecker.di.module

import android.content.Context
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.data.network.RetrofitBuilder.getApi
import com.crostage.trainchecker.data.repository.TrainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RouteModule::class, StationModule::class,
    TrainModule::class, SeatModule::class, FavouriteModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

    @Provides
    @Singleton
    fun provideRetrofit(context: Context): ApiRequests = RetrofitBuilder.getClient(context).getApi

}

