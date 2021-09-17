package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.presentation.model.Event
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Базовая View Model содержащая общий функционал
 *
 */
abstract class BaseViewModel : ViewModel() {
    protected val _error = MutableLiveData<Event<Throwable>>()
    val error: LiveData<Event<Throwable>> = _error

    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    protected val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}