package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * Фабрика для View Model поездов
 *
 */
class TrainViewModelFactory @AssistedInject constructor(
    private val trainInteractor: ITrainInteractor,
    @Assisted owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return when {
            modelClass.isAssignableFrom(TrainViewModel::class.java) -> TrainViewModel(
                trainInteractor,
                handle
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}

/**
 * Фабрика для внедрения зависимостей
 *
 */
@AssistedFactory
interface TrainViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): TrainViewModelFactory
}