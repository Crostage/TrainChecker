package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION
import com.crostage.trainchecker.ConstForTest.Companion.STATION_NAME
import com.crostage.trainchecker.domain.interactors.interfaces.IStationInteractor
import com.crostage.trainchecker.domain.model.Station
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_STATIONS
import io.mockk.*
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StationViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: StationViewModel
    private val interactor: IStationInteractor = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private val exception: Exception = mockk()
    private val stations: Observer<List<Station>> = mockk()
    private val error: Observer<Event<Throwable>> = mockk()
    private val progress: Observer<Boolean> = mockk()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        every { stations.onChanged(any()) } just Runs
        every { error.onChanged(any()) } just Runs
        every { progress.onChanged(any()) } just Runs
        every {
            savedStateHandle.getLiveData<List<Station>>(SAVED_STATE_STATIONS)
        } returns MutableLiveData()
        every { interactor.getLastStationsPick() } returns emptyList()

        viewModel = StationViewModel(interactor, savedStateHandle)

        viewModel.stations.observeForever(stations)
        viewModel.error.observeForever(error)
        viewModel.progress.observeForever(progress)
    }

    @Test
    fun testGetStationResponse() {
        every { interactor.getStationListFromRepo(STATION_NAME) } returns LIST_STATION

        viewModel.getStationResponse(STATION_NAME)

        verifySequence {
            stations.onChanged(emptyList())
            progress.onChanged(true)
            stations.onChanged(LIST_STATION)
            progress.onChanged(false)
        }

        verifySequence { interactor.getStationListFromService(STATION_NAME) wasNot Called }
    }


    @Test
    fun testGetStationResponse_empty_repo() {
        every { interactor.getStationListFromRepo(STATION_NAME) } returns listOf()
        every {
            interactor.getStationListFromService(STATION_NAME)
        } returns Single.just(LIST_STATION)

        viewModel.getStationResponse(STATION_NAME)

        verifySequence {
            stations.onChanged(emptyList())
            progress.onChanged(true)
            stations.onChanged(LIST_STATION)
            progress.onChanged(false)
        }

    }

    @Test
    fun testGetStationResponse_empty_all() {
        every { interactor.getStationListFromRepo(STATION_NAME) } returns listOf()
        every {
            interactor.getStationListFromService(STATION_NAME)
        } returns Single.just(listOf())

        viewModel.getStationResponse(STATION_NAME)

        verifySequence {
            stations.onChanged(emptyList())
            progress.onChanged(true)
            stations.onChanged(listOf())
            progress.onChanged(false)
        }

    }

    @Test
    fun testGetStationResponse_exception() {
        every { interactor.getStationListFromRepo(STATION_NAME) } throws exception

        viewModel.getStationResponse(STATION_NAME)

        verifySequence {
            stations.onChanged(emptyList())
            progress.onChanged(true)
            progress.onChanged(false)
        }
        assertEquals(viewModel.error.value?.getContent(), exception)

    }
}