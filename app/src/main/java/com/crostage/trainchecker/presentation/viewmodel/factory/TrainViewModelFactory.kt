package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import javax.inject.Inject


//todo можно ли что то вынести в даггер?
class TrainViewModelFactory @Inject constructor(
    private val trainInteractor: ITrainInteractor,
    private val favouriteInteractor: IFavouriteInteractor,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrainViewModel::class.java) -> TrainViewModel(
                trainInteractor,
                favouriteInteractor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}