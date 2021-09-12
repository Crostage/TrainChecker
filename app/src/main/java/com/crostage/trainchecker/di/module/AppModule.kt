package com.crostage.trainchecker.di.module

import android.content.Context
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.di.component.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [FavouriteComponent::class, RouteComponent::class, SeatComponent::class,
    StationComponent::class, ResultComponent::class],
    includes = [NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

}

