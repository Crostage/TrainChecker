package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.ConstForTest.Companion.LIST_CAR
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.network.ISeatService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class SeatInteractorTest {

    private val service: ISeatService = mockk()
    private lateinit var interactor: SeatInteractor

    @Before
    fun setUp() {
        interactor = SeatInteractor(service)
    }

    @Test
    fun testGetSeatsRid() {
        every { service.getSeatsRid(TRAIN) } returns RID

        val rid = interactor.getCarsRid(TRAIN)

        verify { service.getSeatsRid(TRAIN) }
        assertEquals(rid, RID)
    }

    @Test
    fun testGetSeatsRid_null() {
        every { service.getSeatsRid(TRAIN) } returns null

        val rid = interactor.getCarsRid(TRAIN)

        verify { service.getSeatsRid(TRAIN) }
        assertEquals(rid, null)
    }

    @Test
    fun testGetSeats() {
        every { service.getSeatsList(RID) } returns LIST_CAR

        val list = interactor.getCars(RID)

        verify { service.getSeatsList(RID) }
        assertEquals(list, LIST_CAR)
    }


}