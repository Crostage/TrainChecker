package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.interactors.interfaces.ISeatInteractor
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_CARS
import io.mockk.*
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeatViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val interactor: ISeatInteractor = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private val listCar: List<Car> = mockk()
    private val exception: Exception = mockk()
    private lateinit var viewModel: SeatViewModel
    private val cars: Observer<List<Car>> = mockk()
    private val error: Observer<Throwable> = mockk()
    private val progress: Observer<Boolean> = mockk()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        every { cars.onChanged(any()) } just Runs
        every { error.onChanged(any()) } just Runs
        every { progress.onChanged(any()) } just Runs

        every {
            savedStateHandle.getLiveData<List<Car>>(SAVED_STATE_CARS)
        } returns MutableLiveData()

        viewModel = SeatViewModel(interactor, savedStateHandle)

        viewModel.cars.observeForever(cars)
        viewModel.error.observeForever(error)
        viewModel.progress.observeForever(progress)
    }

    @Test
    fun testGetCarList() {

        every { interactor.getCarsRid(TRAIN) } returns RID
        every { interactor.getCars(RID) } returns listCar

        viewModel.getCarList(TRAIN)

        verifySequence {
            progress.onChanged(true)
            cars.onChanged(listCar)
            progress.onChanged(false)
        }
        verify { error wasNot Called }
    }

    @Test
    fun testGetCarList_throw_exception() {
        every { interactor.getCarsRid(TRAIN) } returns RID
        every { interactor.getCars(RID) } throws exception

        viewModel.getCarList(TRAIN)

        verifySequence {
            progress.onChanged(true)
            error.onChanged(exception)
            progress.onChanged(false)
        }

    }
}