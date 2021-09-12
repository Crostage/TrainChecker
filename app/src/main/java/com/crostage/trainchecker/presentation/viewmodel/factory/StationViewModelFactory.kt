package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class StationViewModelFactory @AssistedInject constructor(
    private val interactor: IStationInteractor,
    @Assisted owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return when {
            modelClass.isAssignableFrom(StationViewModel::class.java) -> StationViewModel(
                interactor,
                handle
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }

}

@AssistedFactory
interface StationViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): StationViewModelFactory
}