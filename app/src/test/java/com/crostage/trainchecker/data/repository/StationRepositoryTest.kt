package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.STATION_ENTITY
import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.STATION_NAME
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

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
        every { listConverter.revers(listOf(STATION)) } returns listOf(STATION_ENTITY)
        every {
            stationResponseDao.insertStationResponse(StationSearchResponse(STATION_NAME,
                listOf(STATION_ENTITY)))
        } just Runs

        repository.insertStationResponse(STATION_NAME, listOf(STATION))

        verify {
            stationResponseDao.insertStationResponse(StationSearchResponse(STATION_NAME,
                listOf(STATION_ENTITY)))
        }

    }

    @Test
    fun testGetListFromName() {

        every {
            stationResponseDao.getListFromName(STATION_NAME)?.stationList
        } returns listOf(STATION_ENTITY)
        every { listConverter.convert(listOf(STATION_ENTITY)) } returns listOf(STATION)

        val list = repository.getListFromName(STATION_NAME)

        assert(list == listOf(STATION))
    }

    @Test
    fun testGetListFromName_ReturnsNull() {

        every {
            stationResponseDao.getListFromName(STATION_NAME)?.stationList
        } returns null

        val list = repository.getListFromName(STATION_NAME)

        assert(list == null)
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

        every { stationDao.getLastStationPicks().reversed() } returns listOf(STATION_ENTITY)
        every { listConverter.convert(listOf(STATION_ENTITY)) } returns listOf(STATION)
        val list = repository.getLastStationsPick()

        assert(list == listOf(STATION))
    }

}