package com.crostage.trainchecker.domain.interactors

import androidx.lifecycle.LiveData
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.TRAIN
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteInteractorTest {

    private val trainRepository: ITrainRepository = mockk()

    private lateinit var interactor: FavouriteInteractor
    private val liveDataTran: LiveData<List<Train>> = mockk()

    @Before
    fun setUp() {
        interactor = FavouriteInteractor(trainRepository)
    }


    @Test
    fun testGetFavouriteLiveData() {
        every { trainRepository.getFavouriteLiveData() } returns liveDataTran

        val liveData = interactor.getFavouriteLiveData()

        assert(liveData == liveDataTran)
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