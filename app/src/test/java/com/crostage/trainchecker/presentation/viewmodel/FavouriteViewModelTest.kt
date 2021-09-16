package com.crostage.trainchecker.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.interactors.interfaces.IFavouriteInteractor
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.presentation.model.Event
import com.crostage.trainchecker.presentation.util.Helper
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
class FavouriteViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavouriteViewModel

    private val interactor: IFavouriteInteractor = mockk()
    private val exception: Exception = mockk()
    private val favouriteLiveData: LiveData<List<Train>> = mockk()
    private val error: Observer<Throwable> = mockk()
    private val progress: Observer<Boolean> = mockk()
    private val openDetail: Observer<Event<Train>> = mockk()

    @Before
    fun setUp() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        every { error.onChanged(any()) } just Runs
        every { progress.onChanged(any()) } just Runs
        every { openDetail.onChanged(any()) } just Runs

        viewModel = FavouriteViewModel(interactor)

        viewModel.openDetail.observeForever(openDetail)
        viewModel.error.observeForever(error)
        viewModel.progress.observeForever(progress)

    }

    @Test
    fun testRemoveFromFavourite() {
        every { interactor.removeFavourite(TRAIN) } just Runs

        viewModel.removeFromFavourite(TRAIN)

        verify {
            interactor.removeFavourite(TRAIN)
        }

        verify { error wasNot Called }
    }

    @Test
    fun testRemoveFromFavourite_throw_exception() {
        every { interactor.removeFavourite(TRAIN) } throws exception

        viewModel.removeFromFavourite(TRAIN)

        verify {
            interactor.removeFavourite(TRAIN)
            error.onChanged(exception)
        }

    }

    @Test
    fun testTrainClicked() {

        viewModel.trainClicked(TRAIN)

        assert(viewModel.openDetail.value?.getContentIfNotHandled() == TRAIN)

    }

    @Test
    fun testGetFavouriteLiveData() {
        every { interactor.getFavouriteLiveData() } returns favouriteLiveData

        val liveData = viewModel.getFavouriteLiveData()

        verify { interactor.getFavouriteLiveData() }
        assert(liveData == favouriteLiveData)
    }

    @Test
    fun testChekFavouritesOnActualDate() {
        mockkObject(Helper)
        every { Helper.checkFavouritesOnActualDate(LIST_TRAIN) } returns LIST_TRAIN

        val list = viewModel.chekFavouritesOnActualDate(LIST_TRAIN)

        verify {
            Helper.checkFavouritesOnActualDate(LIST_TRAIN)
        }
        assert(list == LIST_TRAIN)

    }

    @Test
    fun testChekFavouritesOnActualDate_have_actual() {
        mockkObject(Helper)
        every { Helper.checkFavouritesOnActualDate(LIST_TRAIN) } returns emptyList()

        val list = viewModel.chekFavouritesOnActualDate(LIST_TRAIN)

        verify {
            Helper.checkFavouritesOnActualDate(LIST_TRAIN)
            viewModel.removeFromFavourite(TRAIN)
        }

        assert(list == emptyList<Train>())
    }

}