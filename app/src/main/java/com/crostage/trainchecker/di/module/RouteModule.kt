package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.converter.StopListConverter
import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.data.network.services.RouteService
import com.crostage.trainchecker.domain.interactors.RouteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.domain.network.IRouteService
import dagger.Binds
import dagger.Module

/**
 * Модуль для предоставления зависимостей для работы с маршрутами
 *
 */
@Module(includes = [RouteBindModule::class])
class RouteModule

/**
 * Вспомогательный модуль для предоставления зависимостей для работы с маршрутами
 */
@Module
interface RouteBindModule {
    /**
     * Предоставляет [RouteInteractor] в качестве [IRouteInteractor]
     *
     */
    @Binds
    fun bindRouteInteractorToIRouteInteractor(routeInteractor: RouteInteractor): IRouteInteractor

    /**
     * Предоставляет [RouteService] в качестве [IRouteService]
     *
     */
    @Binds
    fun bindRouteServiceToIRouteService(routeService: RouteService): IRouteService

    /**
     * Предоставляет [StopListConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindTrainStopToIConverter(trainStopList: StopListConverter)
            : IConverter<List<TrainStopDto>, List<TrainStop>>

}