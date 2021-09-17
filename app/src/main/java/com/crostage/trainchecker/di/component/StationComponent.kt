package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.StationModule
import com.crostage.trainchecker.presentation.fragment.StationFragment
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Вспомогательный компонент для [StationFragment]
 *
 */
@Subcomponent(modules = [StationModule::class])
@StationScope
interface StationComponent {

    /**
     * Внедрение зависимостей во фрагментв
     *
     */
    fun inject(fragment: StationFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): StationComponent
    }

}


@Scope
annotation class StationScope