package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.data.network.services.IStationService
import com.crostage.trainchecker.domain.interactors.IStationInteractor
import com.crostage.trainchecker.presentation.viewmodel.StationViewModel
import java.lang.IllegalArgumentException

class StationViewModelFactory (
    private val interactor: IStationInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(StationViewModel::class.java) -> StationViewModel(
                    interactor
                ) as T
                else -> throw  IllegalArgumentException("Unknown View Model class")
            }
        }
}