package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.FavouriteModule
import com.crostage.trainchecker.di.module.TrainModule
import com.crostage.trainchecker.presentation.fragment.ResultFragment
import dagger.Subcomponent

/**
 * Вспомогательный компонент для [ResultFragment]
 *
 */
@Subcomponent(modules = [TrainModule::class, FavouriteModule::class])
@FavouriteScope
interface ResultComponent {

    /**
     * Внедрение зависимостей во фрагментв
     *
     */
    fun inject(fragment: ResultFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ResultComponent

    }


}

