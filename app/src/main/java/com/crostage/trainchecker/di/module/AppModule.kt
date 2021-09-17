package com.crostage.trainchecker.di.module

import android.content.Context
import com.crostage.trainchecker.data.db.TrainDatabase
import com.crostage.trainchecker.di.component.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Главный модуль для внедрения зависимостей
 *
 */
@Module(subcomponents = [FavouriteComponent::class, RouteComponent::class, CarComponent::class,
    StationComponent::class, ResultComponent::class],
    includes = [NetworkModule::class])
class AppModule {

    /**
     * Предоставляет [TrainDatabase] основной класс для работы с БД
     *
     * @param context контекст приложения
     */
    @Provides
    @Singleton
    fun provideDatabase(context: Context) = TrainDatabase.invoke(context)

}

