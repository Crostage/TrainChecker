package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import javax.inject.Inject

class StationViewModelFactory @Inject constructor(
    private val interactor: IStationInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(
        modelClass: Class<T>,
    ): T {
        return when {
            modelClass.isAssignableFrom(StationViewModel::class.java) -> StationViewModel(
                interactor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }

}
