package com.crostage.trainchecker.di.module

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.converter.TicketConverter
import com.crostage.trainchecker.data.converter.TrainConverter
import com.crostage.trainchecker.data.network.services.TrainService
import com.crostage.trainchecker.data.db.TrainDao
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.domain.interactors.TrainInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.model.data.train.TicketDto
import com.crostage.trainchecker.model.data.train.TrainEntity
import com.crostage.trainchecker.model.domain.Ticket
import com.crostage.trainchecker.model.domain.Train
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [TrainBindModule::class])
class TrainModule {

    @Provides
    fun provideTrainDao(database: TrainDatabase): TrainDao {
        return database.trainDao()
    }

}

@Module
interface TrainBindModule {

    @Binds
    fun bindTrainInteractorToITrainInteractor(trainInteractor: TrainInteractor): ITrainInteractor

    @Binds
    fun bindTrainServiceToITrainService(trainService: TrainService): ITrainService

    @Binds
    fun bindTrainConverterToIConverter(trainConverter: TrainConverter): IConverter<TrainEntity, Train>

    @Binds
    fun bindTicketConverterToIConverter(ticketConverter: TicketConverter): IConverter<List<TicketDto>, List<Ticket>>

}