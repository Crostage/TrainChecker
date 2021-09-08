package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.presentation.viewmodel.FavouriteViewModel
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import javax.inject.Inject

class FavouriteViewModelFactory @Inject constructor(
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