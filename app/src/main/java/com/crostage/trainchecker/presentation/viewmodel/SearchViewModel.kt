package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.util.Helper
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_DATE
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_FROM
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_TO

class SearchViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _stationFrom = savedStateHandle.getLiveData<Station>(SEARCH_SAVED_STATE_FROM)
    val stationFrom: LiveData<Station> = _stationFrom
    private var _stationTo = savedStateHandle.getLiveData<Station>(SEARCH_SAVED_STATE_TO)
    val stationTo: LiveData<Station> = _stationTo
    private val _newDate = savedStateHandle.getLiveData<String>(SEARCH_SAVED_STATE_DATE)
    val newDate: LiveData<String> = _newDate

    init {

        if (_newDate.value == null)
            _newDate.value = Helper.getActualDate()
    }


    fun setDate(date: String) {
        _newDate.value = date
    }

    fun setStationTo(station: Station) {
        _stationTo.value = station
    }

    fun setStationFrom(station: Station) {
        _stationFrom.value = station
    }


}