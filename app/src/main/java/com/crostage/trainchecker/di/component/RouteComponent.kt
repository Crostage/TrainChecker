package com.crostage.trainchecker.di.component

import com.crostage.trainchecker.di.module.RouteModule
import com.crostage.trainchecker.presentation.fragment.DetailFragment
import com.crostage.trainchecker.presentation.fragment.RouteFragment
import dagger.Subcomponent
import javax.inject.Scope

@RouteScope
@Subcomponent(modules = [RouteModule::class])
interface RouteComponent {

    fun inject(fragment: RouteFragment)

}

@Scope
annotation class RouteScope