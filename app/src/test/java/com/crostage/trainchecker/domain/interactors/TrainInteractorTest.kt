package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.ConstForTest.Companion.CODE_FROM
import com.crostage.trainchecker.ConstForTest.Companion.CODE_TO
import com.crostage.trainchecker.ConstForTest.Companion.DATE_START
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.network.ITrainService
import com.crostage.trainchecker.domain.repository.ITrainRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit


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
        assertEquals(result, observable)
    }

    @Test
    fun testGetTrainList() {

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        every { service.getTrainListRid(CODE_FROM, CODE_TO, DATE_START) } returns RID
        every { service.getTrainList(RID) } returns LIST_TRAIN


        interactor.getTrainList(CODE_FROM, CODE_TO, DATE_START)
            .subscribeOn(Schedulers.computation()).test()
        testScheduler.advanceTimeBy(4, TimeUnit.SECONDS)

        verifySequence {
            service.getTrainListRid(CODE_FROM, CODE_TO, DATE_START)
            service.getTrainList(RID)
        }

    }

}