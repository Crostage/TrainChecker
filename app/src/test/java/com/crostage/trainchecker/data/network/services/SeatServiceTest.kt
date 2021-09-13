package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.model.seat.CarResponse
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.TestNetworkConst
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.RID
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.SEAT_RID_RESULT
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.TRAIN
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.utils.Constant
import com.crostage.trainchecker.utils.Constant.Companion.SEAT_LAYER_ID
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
class SeatServiceTest {
    private val retrofitApi: ApiRequests = mockk()
    private val converter: IConverter<List<CarDto>, List<Car>> = mockk()
    private val responseRid: Response<SeatRidResult> = mockk()
    private val responseList: GeneralResult = mockk()
    private val carResponse: CarResponse = mockk()
    private val listCarResponse: List<CarResponse> = mockk()
    private val listCar: List<Car> = mockk()
    private val call: Call<SeatRidResult> = mockk()

    private lateinit var seatService: SeatService

    @Before
    fun setUp() {
        seatService = SeatService(retrofitApi, converter)
        mockkObject(NetworkUtil)
    }

    @Test
    fun testGetSeatsRid_isSuccessful_true() {

        every {
            retrofitApi.getSeats(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart,
                time = TRAIN.timeStart,
                number = TRAIN.trainNumber
            )
        } returns call

        every { call.executeAndExceptionChek() } returns responseRid

        every { responseRid.isSuccessful } returns true
        every { responseRid.body() } returns SEAT_RID_RESULT

        val rid = seatService.getSeatsRid(TRAIN)

        assert(rid == SEAT_RID_RESULT.requestId)

    }

    @Test
    fun testGetSeatsRid_isSuccessful_false() {

        every {
            retrofitApi.getSeats(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart,
                time = TRAIN.timeStart,
                number = TRAIN.trainNumber
            )
        } returns call

        every { call.executeAndExceptionChek() } returns responseRid

        every { responseRid.isSuccessful } returns false

        val rid = seatService.getSeatsRid(TRAIN)

        assert(rid == null)
        verify(exactly = 0) { responseRid.body() }

    }

    @Test
    fun testGetSeatsList() {
        every {
            NetworkUtil.getResponseFromId(SEAT_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.listCarResponse } returns listCarResponse
        every { listCarResponse[0] } returns carResponse
        every { carResponse.cars } returns listOf()
        every { converter.convert(listOf()) } returns listCar

        val list = seatService.getSeatsList(RID)

        assert(list == listCar)
    }

}