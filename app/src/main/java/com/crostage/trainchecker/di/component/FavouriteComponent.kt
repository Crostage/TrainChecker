package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.FavouriteModule
import com.crostage.trainchecker.presentation.fragment.FavouriteFragment
import com.crostage.trainchecker.presentation.fragment.ResultFragment
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [FavouriteModule::class])
@FavouriteScope
interface FavouriteComponent {

    fun inject(fragment: FavouriteFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): FavouriteComponent

    }


}

@Scope
annotation class FavouriteScope