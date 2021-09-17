package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.RouteModule
import com.crostage.trainchecker.presentation.fragment.RouteFragment
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Вспомогательный компонент для [RouteFragment]
 *
 */
@RouteScope
@Subcomponent(modules = [RouteModule::class])
interface RouteComponent {

    /**
     * Внедрение зависимостей во фрагментв
     *
     */
    fun inject(fragment: RouteFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RouteComponent

    }


}

@Scope
annotation class RouteScope