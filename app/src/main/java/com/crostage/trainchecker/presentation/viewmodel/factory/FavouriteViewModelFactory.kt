package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.presentation.viewmodel.FavouriteViewModel
import com.crostage.trainchecker.utils.Constant.Companion.FAVOURITE
import javax.inject.Inject
import javax.inject.Named

/**
 * Фабрика для View Model отслеживаемых поездов
 *
 */
class FavouriteViewModelFactory @Inject constructor(
    @Named(FAVOURITE)
    private val favouriteInteractor: IFavouriteInteractor,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(
                favouriteInteractor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}

