package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.StationModule
import com.crostage.trainchecker.presentation.activity.StationChoiceActivity
import dagger.Subcomponent
import javax.inject.Scope

@StationScope
@Subcomponent(modules = [StationModule::class])
interface StationComponent {

    fun inject(activity: StationChoiceActivity)
}

@Scope
annotation class StationScope