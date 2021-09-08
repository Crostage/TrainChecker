package com.crostage.trainchecker.di.module

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.utils.Constant
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module(includes = [RouteModule::class, StationModule::class,
    TrainModule::class, SeatModule::class, FavouriteModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

    @Provides
    @Singleton
    fun provideRetrofit(cacheDir: File): ApiRequests = RetrofitBuilder.getApi(cacheDir)

    @Provides
    fun provideCacheDir(context: Context) = File(context.cacheDir, Constant.CACHE_CHILD)

}

