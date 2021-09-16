package com.crostage.trainchecker.domain.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteInteractorTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val trainRepository: ITrainRepository = mockk()

    private lateinit var interactor: FavouriteInteractor
    private val liveDataTran: LiveData<List<Train>> = MutableLiveData(LIST_TRAIN)
    private val favourites: Observer<List<Train>> = mockk()

    @Before
    fun setUp() {
        every { favourites.onChanged(any()) } just Runs
        interactor = FavouriteInteractor(trainRepository)
    }


    @Test
    fun testGetFavouriteLiveData() {
        every { trainRepository.getFavouriteLiveData() } returns liveDataTran

        interactor.getFavouriteLiveData().observeForever(favourites)

        verifySequence {
            trainRepository.getFavouriteLiveData()
            favourites.onChanged(LIST_TRAIN)
        }
    }

    @Test
    fun testRemoveFavourite() {
        every { trainRepository.removeFavourite(TRAIN) } just Runs

        interactor.removeFavourite(TRAIN)

        verify { trainRepository.removeFavourite(TRAIN) }
    }

    @Test
    fun testInsetFavourite() {
        every { trainRepository.insertFavourite(TRAIN) } just Runs

        interactor.insertFavourite(TRAIN)

        verify { trainRepository.insertFavourite(TRAIN) }
    }
}