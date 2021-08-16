package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.TrainModule
import com.crostage.trainchecker.presentation.fragment.ResultFragment
import dagger.Subcomponent
import javax.inject.Scope

@TrainScope
@Subcomponent(modules = [TrainModule::class])
interface TrainComponent {

    fun inject(fragment: ResultFragment)
}

@Scope
annotation class TrainScope