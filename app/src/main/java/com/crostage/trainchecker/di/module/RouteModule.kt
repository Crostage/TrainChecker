package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.network.services.RouteService
import com.crostage.trainchecker.domain.interactors.RouteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.network.IRouteService
import dagger.Binds
import dagger.Module

@Module(includes = [RouteBindModule::class])
class RouteModule

@Module
interface RouteBindModule {
    @Binds
    fun bindRouteInteractorToIRouteInteractor(routeInteractor: RouteInteractor): IRouteInteractor

    @Binds
    fun bindRouteServiceToIRouteService(routeService: RouteService): IRouteService


}