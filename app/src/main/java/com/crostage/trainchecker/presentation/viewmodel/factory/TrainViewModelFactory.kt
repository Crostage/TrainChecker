package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.presentation.viewmodel.TrainViewModel

class TrainViewModelFactory(
    private val interactor: ITrainInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrainViewModel::class.java) -> TrainViewModel(
                interactor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}