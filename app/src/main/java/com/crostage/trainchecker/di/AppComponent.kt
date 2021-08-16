package com.crostage.trainchecker.di

import android.content.Context
import com.crostage.trainchecker.di.component.RouteComponent
import com.crostage.trainchecker.di.component.StationComponent
import com.crostage.trainchecker.di.component.TrainComponent
import com.crostage.trainchecker.di.module.AppModule
import com.crostage.trainchecker.presentation.activity.StationChoiseActivity
import com.crostage.trainchecker.presentation.fragment.ResultFragment
import com.crostage.trainchecker.presentation.fragment.RouteFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun getTrainComponent(): TrainComponent
    fun getRouteComponent(): RouteComponent
    fun getStationComponent(): StationComponent


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}