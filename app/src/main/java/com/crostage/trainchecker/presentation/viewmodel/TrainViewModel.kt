package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.interactors.ITrainInteractor
import com.crostage.trainchecker.model.train.Train
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TrainViewModel(private val interactor: ITrainInteractor) : ViewModel() {

    private var _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>> = _trains

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    fun trainsFromSearchRequest(codeFrom: Int, codeTo: Int, date: String) {

        Single.fromCallable {
            interactor.getTrainList(codeFrom, codeTo, date)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doFinally { _progress.value = false }.doOnSubscribe { _progress.value = true }
            .subscribe(
                _trains::setValue, _error::setValue
            )

    }


}