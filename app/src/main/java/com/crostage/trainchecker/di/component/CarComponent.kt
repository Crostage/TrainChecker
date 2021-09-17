package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.CarModule
import com.crostage.trainchecker.presentation.fragment.CarFragment
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Вспомогательный компонент для [CarFragment]
 *
 */
@SeatScope
@Subcomponent(modules = [CarModule::class])
interface CarComponent {

    /**
     * Внедрение зависимостей во фрагментв
     *
     */
    fun inject(fragment: CarFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): CarComponent
    }
}

@Scope
annotation class SeatScope