package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.util.Helper

class SearchViewModel : ViewModel() {

    private var stationTo: Station? = null
    private var stationFrom: Station? = null

    private val _newDate = MutableLiveData<String>()
    val newDate: LiveData<String> = _newDate

    init {
        _newDate.value = Helper.getActualDate()
    }

    fun setDate(date: String) {
        _newDate.value = date
    }

    fun setStationTo(station: Station) {
        stationTo = station
    }

    fun setStationFrom(station: Station) {
        stationFrom = station
    }

    fun getStationTo(): Station? {
        return stationTo
    }

    fun getStationFrom(): Station? {
        return stationFrom
    }

}