package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.CAR
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.LIST_CAR
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.RID
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN
import com.crostage.trainchecker.domain.network.ISeatService
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

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

        val rid = interactor.getSeatsRid(TRAIN)

        assert(rid == RID)
    }

    @Test
    fun testGetSeatsRid_null() {
        every { service.getSeatsRid(TRAIN) } returns null

        val rid = interactor.getSeatsRid(TRAIN)

        assert(rid == null)
    }

    @Test
    fun testGetSeats() {
        every { service.getSeatsList(RID) } returns LIST_CAR

        val list = interactor.getSeats(RID)

        assert(list == LIST_CAR)
    }


}