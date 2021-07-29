package com.crostage.trainchecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.repository.TrainDatabase
import com.crostage.trainchecker.repository.TrainRepository
import java.lang.IllegalArgumentException

class TrainViewModelFactory(private val repository: TrainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainViewModel::class.java))
            return TrainViewModel(repository) as T
        else
            throw  IllegalArgumentException("Unknown View Model class")
    }
}