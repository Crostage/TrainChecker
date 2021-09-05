package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.FavouriteInteractor
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel
import javax.inject.Inject

class TrainViewModelFactory @Inject constructor(
    private val trainInteractor: ITrainInteractor,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrainViewModel::class.java) -> TrainViewModel(
                trainInteractor,
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}