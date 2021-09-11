package com.crostage.trainchecker.di.module

import android.content.Context
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.presentation.MainApp
import com.crostage.trainchecker.utils.Constant
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideCacheDir(context: Context) = File(context.cacheDir, Constant.CACHE_CHILD)


    @Provides
    @Singleton
    fun provideRetrofit(cacheDir: File): ApiRequests =
        RetrofitBuilder(MainApp.connectionType()).getApi(cacheDir)
}