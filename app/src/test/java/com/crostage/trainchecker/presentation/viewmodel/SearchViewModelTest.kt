package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.ConstForTest.Companion.STATION
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.util.Helper
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_DATE
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_FROM
import com.crostage.trainchecker.utils.Constant.Companion.SEARCH_SAVED_STATE_TO
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val stationFrom: Observer<Station> = mockk()
    private val stationTo: Observer<Station> = mockk()
    private val newDate: Observer<String> = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        every { stationFrom.onChanged(any()) } just Runs
        every { stationTo.onChanged(any()) } just Runs
        every { newDate.onChanged(any()) } just Runs
        every {
            savedStateHandle.getLiveData<Station>(SEARCH_SAVED_STATE_FROM)
        } returns MutableLiveData()
        every {
            savedStateHandle.getLiveData<Station>(SEARCH_SAVED_STATE_TO)
        } returns MutableLiveData()
        every {
            savedStateHandle.getLiveData<String>(SEARCH_SAVED_STATE_DATE)
        } returns MutableLiveData()

        mockkObject(Helper)
        every { Helper.getActualDate() } returns ACTUAL_DATE

        viewModel = SearchViewModel(savedStateHandle)

        viewModel.stationFrom.observeForever(stationFrom)
        viewModel.stationTo.observeForever(stationTo)
        viewModel.newDate.observeForever(newDate)
    }

    @Test
    fun testSetDate() {

        viewModel.setDate(NEW_DATE)
        verifySequence {
            newDate.onChanged(ACTUAL_DATE)
            newDate.onChanged(NEW_DATE)
        }
    }

    @Test
    fun testSetStationTo() {
        viewModel.setStationTo(STATION)

        verify { stationTo.onChanged(STATION) }
    }

    @Test
    fun setStationFrom() {
        viewModel.setStationFrom(STATION)

        verify { stationFrom.onChanged(STATION) }
    }


    companion object {
        private const val ACTUAL_DATE = "YESTERDAY"
        private const val NEW_DATE = "20.12.2021"
    }
}