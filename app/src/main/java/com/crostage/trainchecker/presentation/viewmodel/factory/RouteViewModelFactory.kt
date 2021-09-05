package com.crostage.trainchecker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.presentation.viewmodel.RouteViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RouteViewModelFactory @Inject constructor(
    private val interactor: IRouteInteractor,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RouteViewModel::class.java) -> RouteViewModel(
                interactor
            ) as T
            else -> throw  IllegalArgumentException("Unknown View Model class")
        }
    }

}