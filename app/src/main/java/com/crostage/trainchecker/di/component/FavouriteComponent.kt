package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.presentation.fragment.FavouriteFragment
import dagger.Subcomponent
import javax.inject.Scope

@FavouriteScope
@Subcomponent
interface FavouriteComponent {

    fun inject(fragment: FavouriteFragment)

}

@Scope
annotation class FavouriteScope