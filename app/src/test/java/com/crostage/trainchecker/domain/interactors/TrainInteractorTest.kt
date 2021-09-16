package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.ConstForTest.Companion.CODE_FROM
import com.crostage.trainchecker.ConstForTest.Companion.CODE_TO
import com.crostage.trainchecker.ConstForTest.Companion.DATE_START
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.mockk.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TrainInteractorTest {

    private lateinit var interactor: TrainInteractor

    private val service: ITrainService = mockk()
    private val repository: ITrainRepository = mockk()
    private val observable: Observable<List<Train>> = mockk()

    @Before
    fun setUp() {
        interactor = TrainInteractor(service, repository)
    }

    @Test
    fun testGetFavouriteObservable() {
        every { repository.getFavouriteObservable() } returns observable

        val result = interactor.getFavouriteObservable()

        verify { repository.getFavouriteObservable() }
        assert(result == observable)
    }

    @Test
    fun testGetTrainList() {
        every { service.getTrainListRid(CODE_FROM, CODE_TO, DATE_START) } returns RID
        every { service.getTrainList(RID) } returns LIST_TRAIN


        val result = interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START).blockingGet()

        verifySequence {
            service.getTrainListRid(CODE_FROM, CODE_TO, DATE_START)
            service.getTrainList(RID)
        }

        assert(result == LIST_TRAIN)

    }

}