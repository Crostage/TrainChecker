package com.crostage.trainchecker.di.component

import android.app.Activity
import com.crostage.trainchecker.di.AppComponent
import com.crostage.trainchecker.di.module.StationModule
import com.crostage.trainchecker.presentation.activity.StationChoiceActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Scope

@StationScope
@Subcomponent(modules = [StationModule::class])
interface StationComponent {

    fun inject(activity: StationChoiceActivity)
}

@Scope
annotation class StationScope