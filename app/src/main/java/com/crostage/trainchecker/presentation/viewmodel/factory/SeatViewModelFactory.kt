package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.presentation.viewmodel.SeatViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SeatViewModelFactory @AssistedInject constructor(
    private val interactor: ISeatInteractor,
    @Assisted owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return when {
            modelClass.isAssignableFrom(SeatViewModel::class.java) -> SeatViewModel(
                interactor,
                handle
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }

}


@AssistedFactory
interface SeatViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): SeatViewModelFactory
}