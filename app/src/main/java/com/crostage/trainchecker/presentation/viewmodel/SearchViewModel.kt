package com.crostage.trainchecker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.crostage.trainchecker.data.model.station.Station
import com.crostage.trainchecker.utils.Helper

class SearchViewModel : ViewModel() {

    private  var stationTo: Station? = null
    private  var stationFrom: Station? = null
    private var date = Helper.getActualDate()

    fun setStationTo(station: Station) {
        stationTo = station
    }

    fun setStationFrom(station: Station) {
        stationFrom = station
    }

    fun setDate(newDate: String) {
        date = newDate
    }

    fun getStationTo(): Station? {
        return stationTo
    }

    fun getStationFrom(): Station? {
        return stationFrom
    }

    fun getDate(): String {
        return date
    }


}