package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crostage.trainchecker.data.model.routRequset.TrainStop
import com.crostage.trainchecker.data.model.trainRequest.Train
import com.crostage.trainchecker.data.network.TrainService
import com.crostage.trainchecker.data.repository.TrainRepository
import kotlinx.coroutines.launch

class RouteViewModel(
    private val repository: TrainRepository,
    private val responses: TrainService
) : ViewModel() {


    private var _routes = MutableLiveData<List<TrainStop>>()
    val routes: LiveData<List<TrainStop>> = _routes

    private var _error = MutableLiveData<Exception>()
    val error: LiveData<java.lang.Exception> = _error

    fun getRoutes(train: Train) {
        viewModelScope.launch {
            _routes.postValue(responses.getTrainRoutes(train))
        }
    }
}