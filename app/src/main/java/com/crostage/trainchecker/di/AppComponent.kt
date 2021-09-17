package com.crostage.trainchecker.di

import android.content.Context
import com.crostage.trainchecker.di.component.*
import com.crostage.trainchecker.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Главный интерфейс компонента приложения
 */
@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    /**
     * Методы для создания вспомогательных компонентов приложения
     *
     */
    fun getTrainComponent(): ResultComponent.Builder
    fun getRouteComponent(): RouteComponent.Builder
    fun getStationComponent(): StationComponent.Builder
    fun getSeatComponent(): CarComponent.Builder
    fun getFavouriteComponent(): FavouriteComponent.Builder


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}


