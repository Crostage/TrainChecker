package com.crostage.trainchecker.domain.interactors

import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION
import com.crostage.trainchecker.ConstForTest.Companion.STATION
import com.crostage.trainchecker.ConstForTest.Companion.STATION_NAME
import com.crostage.trainchecker.domain.network.IStationService
import com.crostage.trainchecker.domain.repository.IStationRepository
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StationInteractorTest {

    private val service: IStationService = mockk()
    private val repository: IStationRepository = mockk()
    private lateinit var interactor: StationInteractor

    @Before
    fun setUp() {
        interactor = StationInteractor(service, repository)
    }

    @Test
    fun testGetStationListFromRepo() {
        every { repository.getListFromName(STATION_NAME) } returns LIST_STATION

        val list = interactor.getStationListFromRepo(STATION_NAME)

        verify { repository.getListFromName(STATION_NAME) }
        assertEquals(list, LIST_STATION)
    }

    @Test
    fun testGetStationListFromRepo_null() {
        every { repository.getListFromName(STATION_NAME) } returns null

        val list = interactor.getStationListFromRepo(STATION_NAME)

        verify { repository.getListFromName(STATION_NAME) }
        assertEquals(list, emptyList())
    }

    @Test
    fun testGetStationListFromService() {
        every { service.getStationList(STATION_NAME) } returns LIST_STATION
        every { repository.insertStationResponse(STATION_NAME, LIST_STATION) } just Runs

        val result = interactor.getStationListFromService(STATION_NAME).blockingGet()

        verify {
            service.getStationList(STATION_NAME)
            repository.insertStationResponse(STATION_NAME, LIST_STATION)
        }
        assertEquals(result, LIST_STATION)
    }

    @Test
    fun testGetStationListFromService_empty_list() {
        every { service.getStationList(STATION_NAME) } returns emptyList()

        val result = interactor.getStationListFromService(STATION_NAME).blockingGet()

        verify { service.getStationList(STATION_NAME) }
        verify(exactly = 0) { repository.insertStationResponse(STATION_NAME, emptyList()) }
        assertEquals(result, emptyList())
    }

    @Test
    fun testInsertStation() {
        every { repository.insertStation(STATION) } just Runs

        interactor.insertStation(STATION)

        verify { repository.insertStation(STATION) }
    }


    @Test
    fun testGetLastStationsPick() {
        every { repository.getLastStationsPick() } returns LIST_STATION

        val list = interactor.getLastStationsPick()

        verify { repository.getLastStationsPick() }
        assertEquals(list, LIST_STATION)
    }

}