package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.presentation.viewmodel.SeatViewModel
import javax.inject.Inject

class SeatViewModelFactory @Inject constructor(
    private val interactor: ISeatInteractor,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SeatViewModel::class.java) -> SeatViewModel(
                interactor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }

}