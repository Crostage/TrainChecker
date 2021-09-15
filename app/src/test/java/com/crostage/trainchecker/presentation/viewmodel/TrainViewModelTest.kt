package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.ConstForTest
import com.crostage.trainchecker.ConstForTest.Companion.CODE_FROM
import com.crostage.trainchecker.ConstForTest.Companion.CODE_TO
import com.crostage.trainchecker.ConstForTest.Companion.DATE_START
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.interactors.interfaces.ITrainInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_TRAINS
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

@RunWith(MockitoJUnitRunner::class)
class TrainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TrainViewModel

    private val interactor: ITrainInteractor = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private val exception: Exception = mockk()

    private val trains: Observer<List<Train>> = mockk()
    private val error: Observer<Throwable> = mockk()
    private val progress: Observer<Boolean> = mockk()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        every { trains.onChanged(any()) } just Runs
        every { error.onChanged(any()) } just Runs
        every { progress.onChanged(any()) } just Runs
        every {
            savedStateHandle.getLiveData<List<Train>>(SAVED_STATE_TRAINS)
        } returns MutableLiveData()

        viewModel = TrainViewModel(interactor, savedStateHandle)

        viewModel.trains.observeForever(trains)
        viewModel.error.observeForever(error)
        viewModel.progress.observeForever(progress)

    }

    @Test
    fun testTrainsFromSearchRequest() {

        every {
            interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START)
        } returns Single.just(LIST_TRAIN)

        viewModel.trainsFromSearchRequest(CODE_FROM, CODE_TO, DATE_START)

        verifySequence {
            interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START)
            progress.onChanged(true)
            trains.onChanged(LIST_TRAIN)
            progress.onChanged(false)
        }

        verify { error wasNot Called }

    }


    @Test
    fun testTrainsFromSearchRequest_exception() {

        every {
            interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START)
        } throws exception

        viewModel.trainsFromSearchRequest(CODE_FROM, CODE_TO, DATE_START)

        verifySequence {
            interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START)
            progress.onChanged(true)
            error.onChanged(exception)
            progress.onChanged(false)
        }

    }


    @Test
    fun testInsertToFavourite() {
        every { interactor.insertFavourite(TRAIN) } just Runs

        viewModel.insertToFavourite(TRAIN)

        verify { interactor.insertFavourite(TRAIN) }
        verify { error wasNot Called }
    }

    @Test
    fun testInsertToFavourite_exception() {
        every { interactor.insertFavourite(TRAIN) } throws exception

        viewModel.insertToFavourite(TRAIN)

        verifySequence {
            interactor.insertFavourite(TRAIN)
            error.onChanged(exception)
        }
    }

//    @Test
//    fun testCheckFavouritesContainsTrains(){
//        every { trains.va }
//    }


}