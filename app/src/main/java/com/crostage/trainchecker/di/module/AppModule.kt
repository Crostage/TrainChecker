package com.crostage.trainchecker.di.module

import android.content.Context
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.crostage.trainchecker.data.network.RetrofitBuilder.getApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RouteModule::class, StationModule::class,
    TrainModule::class, SeatModule::class, FavouriteModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

    @Provides
    @Singleton
    fun provideRetrofit(context: Context): ApiRequests = RetrofitBuilder.getClient(context).getApi


}
//todo разобраться куда лучше вставить
//@Module
//interface AppBindModule {
//    @Binds
//    fun bindTrainConverterToIConverter(trainConverter: TrainConverter): IConverter<TrainEntity, Train>
//
//    @Binds
//    fun bindTicketConverterToIConverter(ticketConverter: TicketConverter): IConverter<List<TicketDto>, List<Ticket>>
//
//}

