package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.data.network.services.IRouteService
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import java.lang.IllegalArgumentException

class RouteViewModelFactory (
    private val service: IRouteService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(RouteViewModel::class.java) -> RouteViewModel(
                    service
                ) as T
                else -> throw  IllegalArgumentException("Unknown View Model class")
            }
        }

}