package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.FavouriteConverter
import com.crostage.trainchecker.data.converter.FavouriteListConverter
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.repository.TrainRepository
import com.crostage.trainchecker.di.component.FavouriteScope
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.utils.Constant.Companion.FAVOURITE
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Модуль для внедрения зависимостей в отслеживаемые
 *
 */
@Module(includes = [FavouriteBindModule::class])
class FavouriteModule {

    /**
     * Предоставляет [TrainDao]
     *
     * @param database [TrainDatabase] класс базы данных
     * @return [TrainDao] класс для работы с таблицей поездов
     */
    @Provides
    @FavouriteScope
    fun provideTrainDao(database: TrainDatabase): TrainDao {
        return database.trainDao()
    }

}

/**
 * Вспомогательный модуль для для внедрения зависимостей в отслеживаемые
 *
 */
@Module
interface FavouriteBindModule {

    /**
     * Предоставляет [FavouriteInteractor] в качестве [IFavouriteInteractor]
     *
     */
    @Binds
    @Named(FAVOURITE)
    fun bindFavouriteInteractorToIFavouriteInteractor(
        favouriteInteractor: FavouriteInteractor,
    ): IFavouriteInteractor

    /**
     * Предоставляет [ITrainInteractor] в качестве [IFavouriteInteractor]
     *
     */
    @Binds
    fun bindITrainInteractorToIFavouriteInteractor(
        trainInteractor: ITrainInteractor,
    ): IFavouriteInteractor

    /**
     * Предоставляет [TrainRepository] в качестве [ITrainRepository]
     *
     */
    @Binds
    fun bindFavouriteServiceToIFavouriteService(trainRepository: TrainRepository): ITrainRepository

    /**
     * Предоставляет [FavouriteConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindFavouriteConverterToIConverter(
        favouriteConverter: FavouriteConverter,
    ): IConverter<FavouriteEntity, Train>

    /**
     * Предоставляет [FavouriteListConverter] в качестве [IConverter]
     *
     */
    @Binds
    fun bindFavouriteListConverterToIConverter(favouriteListConverter: FavouriteListConverter)
            : IConverter<List<FavouriteEntity>, List<Train>>
}
