package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION
import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.STATION
import com.crostage.trainchecker.ConstForTest.Companion.STATION_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.STATION_NAME
import com.crostage.trainchecker.ConstForTest.Companion.STATION_SEARCH_RESPONSE
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.domain.model.Station
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class StationRepositoryTest {

    private val stationResponseDao: StationResponseDao = mockk()
    private val stationDao: StationDao = mockk()
    private val converter: IConverter<StationEntity, Station> = mockk()
    private val listConverter: IConverter<List<StationEntity>, List<Station>> = mockk()
    private lateinit var repository: StationRepository

    @Before
    fun setUp() {
        repository = StationRepository(stationResponseDao, stationDao, converter, listConverter)
    }

    @Test
    fun testInsertStationResponse() {

        every { listConverter.revers(LIST_STATION) } returns LIST_STATION_ENTITY
        every {
            stationResponseDao.insertStationResponse(STATION_SEARCH_RESPONSE)
        } just Runs

        repository.insertStationResponse(STATION_NAME, LIST_STATION)

        verify {
            stationResponseDao.insertStationResponse(STATION_SEARCH_RESPONSE)
        }

    }

    @Test
    fun testGetListFromName() {

        every {
            stationResponseDao.getListFromName(STATION_NAME)?.stationList
        } returns LIST_STATION_ENTITY
        every { listConverter.convert(LIST_STATION_ENTITY) } returns LIST_STATION

        val list = repository.getListFromName(STATION_NAME)

        verify { stationResponseDao.getListFromName(STATION_NAME) }
        assertEquals(list, LIST_STATION)
    }

    @Test
    fun testGetListFromName_ReturnsNull() {

        every {
            stationResponseDao.getListFromName(STATION_NAME)?.stationList
        } returns null

        val list = repository.getListFromName(STATION_NAME)

        verify { stationResponseDao.getListFromName(STATION_NAME) }
        assertEquals(list, null)
    }

    @Test
    fun testInsetStation() {

        every { converter.revers(STATION) } returns STATION_ENTITY
        every { stationDao.insertStation(STATION_ENTITY) } just Runs

        repository.insertStation(STATION)

        verify { stationDao.insertStation(STATION_ENTITY) }
    }

    @Test
    fun testGetLastStationPick() {

        every { stationDao.getLastStationPicks().reversed() } returns LIST_STATION_ENTITY
        every { listConverter.convert(LIST_STATION_ENTITY) } returns LIST_STATION
        val list = repository.getLastStationsPick()

        verify { stationDao.getLastStationPicks() }
        assertEquals(list, LIST_STATION)
    }

}