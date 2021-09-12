package com.crostage.trainchecker.di

import android.content.Context
import com.crostage.trainchecker.di.component.*
import com.crostage.trainchecker.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun getTrainComponent(): ResultComponent.Builder
    fun getRouteComponent(): RouteComponent.Builder
    fun getStationComponent(): StationComponent.Builder
    fun getSeatComponent(): SeatComponent.Builder
    fun getFavouriteComponent(): FavouriteComponent.Builder


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}


