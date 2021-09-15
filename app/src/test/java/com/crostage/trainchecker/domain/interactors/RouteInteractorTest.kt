package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.domain.network.IRouteService
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RouteInteractorTest {
    private val service: IRouteService = mockk()

    private lateinit var interactor: RouteInteractor

    @Before
    fun setUp() {
        interactor = RouteInteractor(service)
    }

    @Test
    fun testGetRouteListRid() {
        every { service.getRouteListRequestId(TRAIN) } returns RID

        val rid = interactor.getRouteListRid(TRAIN)

        assert(rid == RID)
    }

    @Test
    fun testGetRouteListRid_null() {
        every { service.getRouteListRequestId(TRAIN) } returns null

        val rid = interactor.getRouteListRid(TRAIN)

        assert(rid == null)
    }

    @Test
    fun testGetRoutesList() {
        every { service.getRoutesList(RID) } returns LIST_TRAIN_STOP

        val list = interactor.getRoutesList(RID)

        assert(list == LIST_TRAIN_STOP)
    }
}