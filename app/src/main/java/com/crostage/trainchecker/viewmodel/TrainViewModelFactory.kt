package com.crostage.trainchecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.data.network.TrainResponses
import com.crostage.trainchecker.data.repository.TrainRepository
import java.lang.IllegalArgumentException

class TrainViewModelFactory(private val repository: TrainRepository, private val responses: TrainResponses) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainViewModel::class.java))
            return TrainViewModel(repository,responses) as T
        else
            throw  IllegalArgumentException("Unknown View Model class")
    }
}