package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.data.network.ITrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: TrainRepository,
    private val responses: ITrainService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrainViewModel::class.java) -> TrainViewModel(
                repository,
                responses
            ) as T
            modelClass.isAssignableFrom(RouteViewModel::class.java) -> RouteViewModel(
                repository,
                responses
            ) as T
            modelClass.isAssignableFrom(StationViewModel::class.java) -> StationViewModel(
                repository,
                responses
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }
}