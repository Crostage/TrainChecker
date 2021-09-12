package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class RouteViewModelFactory @AssistedInject constructor(
    private val interactor: IRouteInteractor,
    @Assisted owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return when {
            modelClass.isAssignableFrom(RouteViewModel::class.java) -> RouteViewModel(
                interactor,
                handle
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }

    }
}


@AssistedFactory
interface RouteViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): RouteViewModelFactory
}