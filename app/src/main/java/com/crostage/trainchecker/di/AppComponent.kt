package com.crostage.trainchecker.di

import android.content.Context
import com.crostage.trainchecker.di.module.AppModule
import com.crostage.trainchecker.presentation.activity.StationChoiseActivity
import com.crostage.trainchecker.presentation.fragments.ResultFragment
import com.crostage.trainchecker.presentation.fragments.RoutesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: ResultFragment)
    fun inject(fragment: RoutesFragment)
    fun inject(activity: StationChoiseActivity)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}