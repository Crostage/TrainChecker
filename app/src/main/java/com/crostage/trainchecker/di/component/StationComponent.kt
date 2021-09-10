package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.StationModule
import com.crostage.trainchecker.presentation.fragment.StationFragment
import dagger.Subcomponent
import javax.inject.Scope

@StationScope
@Subcomponent(modules = [StationModule::class])
interface StationComponent {

    fun inject(fragment: StationFragment)
}

@Scope
annotation class StationScope