package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.SeatModule
import com.crostage.trainchecker.presentation.fragment.SeatFragment
import dagger.Subcomponent
import javax.inject.Scope

@SeatScope
@Subcomponent(modules = [SeatModule::class])
interface SeatComponent {

    fun inject(fragment: SeatFragment): SeatFragment

}

@Scope
annotation class SeatScope