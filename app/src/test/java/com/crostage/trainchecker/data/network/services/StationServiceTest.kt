package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION
import com.crostage.trainchecker.ConstForTest.Companion.LIST_STATION_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.STATION_NAME
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Station
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class StationServiceTest {


    private val retrofitApi: ApiRequests = mockk()
    private val converter: IConverter<List<StationEntity>, List<Station>> = mockk()
    private val responseList: Response<List<StationEntity>> = mockk()
    private val call: Call<List<StationEntity>> = mockk()

    private lateinit var stationService: StationService

    @Before
    fun setUp() {
        stationService = StationService(retrofitApi, converter)
        mockkObject(NetworkUtil)
    }


    @Test
    fun testGetStationList_isSuccessful_true() {

        every {
            retrofitApi.getStation(stationName = STATION_NAME)
        } returns call

        every { call.executeAndExceptionChek() } returns responseList

        every { responseList.isSuccessful } returns true
        every { responseList.body() } returns LIST_STATION_ENTITY
        every { converter.convert(LIST_STATION_ENTITY) } returns LIST_STATION

        val list = stationService.getStationList(STATION_NAME)

        assert(list == LIST_STATION)

    }

    @Test
    fun testGetStationList_isSuccessful_false() {

        every {
            retrofitApi.getStation(stationName = STATION_NAME)
        } returns call

        every { call.executeAndExceptionChek() } returns responseList

        every { responseList.isSuccessful } returns false

        val list = stationService.getStationList(STATION_NAME)

        assert(list == emptyList<Station>())
        verify(exactly = 0) { responseList.body() }

    }

}