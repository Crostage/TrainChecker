package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    protected val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}