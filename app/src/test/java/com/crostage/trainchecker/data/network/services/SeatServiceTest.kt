package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.LIST_CAR
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.LIST_CAR_DTO
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.RID
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.SEAT_RID_RESULT
import com.crostage.trainchecker.data.converter.ConverterConst.Companion.TRAIN
import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.rid.SeatRidResult
import com.crostage.trainchecker.data.model.seat.CarDto
import com.crostage.trainchecker.data.model.seat.CarResponse
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Car
import com.crostage.trainchecker.utils.Constant.Companion.SEAT_LAYER_ID
import com.crostage.trainchecker.utils.ServerSendError
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
import kotlin.test.assertFailsWith

@RunWith(MockitoJUnitRunner::class)
class SeatServiceTest {
    private val retrofitApi: ApiRequests = mockk()
    private val converter: IConverter<List<CarDto>, List<Car>> = mockk()
    private val responseRid: Response<SeatRidResult> = mockk()
    private val responseList: GeneralResult = mockk()
    private val carResponse: CarResponse = mockk()
    private val listCarResponse: List<CarResponse> = mockk()
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
        every { carResponse.cars } returns LIST_CAR_DTO
        every { converter.convert(LIST_CAR_DTO) } returns LIST_CAR

        val list = seatService.getSeatsList(RID)

        assert(list == LIST_CAR)
    }


    @Test
    fun testGetSeatsList_null() {
        every {
            NetworkUtil.getResponseFromId(SEAT_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.listCarResponse } returns null

        val list = seatService.getSeatsList(RID)

        assert(list == emptyList<Car>())
    }

    @Test
    fun testGetSeatsList_throw_exception() {
        every {
            NetworkUtil.getResponseFromId(SEAT_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.listCarResponse } returns listCarResponse
        every { listCarResponse[0] } throws IndexOutOfBoundsException()

        assertFailsWith<ServerSendError> { seatService.getSeatsList(RID) }

    }

}