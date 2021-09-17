package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.FavouriteModule
import com.crostage.trainchecker.presentation.fragment.FavouriteFragment
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Вспомогательный компонент для [FavouriteFragment]
 *
 */
@Subcomponent(modules = [FavouriteModule::class])
@FavouriteScope
interface FavouriteComponent {

    /**
     * Внедрение зависимостей во фрагментв
     *
     */
    fun inject(fragment: FavouriteFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): FavouriteComponent

    }


}

@Scope
annotation class FavouriteScope