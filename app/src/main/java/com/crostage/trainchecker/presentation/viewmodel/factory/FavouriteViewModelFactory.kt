package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.presentation.viewmodel.FavouriteViewModel
import com.crostage.trainchecker.utils.Constant.Companion.FAVOURITE
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Named

class FavouriteViewModelFactory @AssistedInject constructor(
    @Named(FAVOURITE)
    private val favouriteInteractor: IFavouriteInteractor,
    @Assisted owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(
                favouriteInteractor,
                handle
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}

@AssistedFactory
interface FavouriteViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): FavouriteViewModelFactory
}