package com.crostage.trainchecker.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Constant.Companion.CONNECTION_TYPE
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RouteModule::class, StationModule::class,
    TrainModule::class, SeatModule::class, FavouriteModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

    @Provides
    @Singleton
    fun provideRetrofit(cacheDir: File): ApiRequests = RetrofitBuilder().getApi(cacheDir)

    @Provides
    @Singleton
    fun provideCacheDir(context: Context) = File(context.cacheDir, Constant.CACHE_CHILD)

    @Provides
    @Named(CONNECTION_TYPE)
    fun provideConnectionType(context: Context): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            val capabilities =
                cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        result = 2
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        result = 1
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                        result = 3
                    }
                }
            }

        }
        return result
    }


}

