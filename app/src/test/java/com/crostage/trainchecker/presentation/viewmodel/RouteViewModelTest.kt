package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.RID
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN
import com.crostage.trainchecker.domain.interactors.interfaces.IRouteInteractor
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.utils.Constant.Companion.SAVED_STATE_STOPS
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
class RouteViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private lateinit var viewModel: RouteViewModel

    private val interactor: IRouteInteractor = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private val listTrainStop: List<TrainStop> = mockk()
    private val exception: Exception = mockk()

    private val routes: Observer<List<TrainStop>> = mockk()
    private val error: Observer<Throwable> = mockk()
    private val progress: Observer<Boolean> = mockk()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        every { routes.onChanged(any()) } just Runs
        every { error.onChanged(any()) } just Runs
        every { progress.onChanged(any()) } just Runs
        every {
            savedStateHandle.getLiveData<List<TrainStop>>(SAVED_STATE_STOPS)
        } returns MutableLiveData()

        viewModel = RouteViewModel(interactor, savedStateHandle)

        viewModel.routes.observeForever(routes)
        viewModel.error.observeForever(error)
        viewModel.progress.observeForever(progress)


    }

    @Test
    fun testGetRoutes() {
        every { interactor.getRouteListRid(TRAIN) } returns RID
        every { interactor.getRoutesList(RID) } returns listTrainStop

        viewModel.getRoutes(TRAIN)

        verifySequence {
            progress.onChanged(true)
            routes.onChanged(listTrainStop)
            progress.onChanged(false)
        }


        verify { error wasNot Called }

    }


    @Test
    fun testGetRoutes_with_exception() {
        every { interactor.getRouteListRid(TRAIN) } returns RID
        every { interactor.getRoutesList(RID) } throws exception

        viewModel.getRoutes(TRAIN)

        verifySequence {
            progress.onChanged(true)
            error.onChanged(exception)
            progress.onChanged(false)
        }

    }

}