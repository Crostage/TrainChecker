package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP_DTO
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.ConstForTest.Companion.ROUTES_ERROR
import com.crostage.trainchecker.ConstForTest.Companion.ROUTE_RID_RESULT
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.rid.RouteRidResult
import com.crostage.trainchecker.data.model.rout.TrainStopDto
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.TrainStop
import com.crostage.trainchecker.utils.Constant.Companion.ROUTE_LAYER_ID
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
class RouteServiceTest {

    private val retrofitApi: ApiRequests = mockk()
    private val converter: IConverter<List<TrainStopDto>, List<TrainStop>> = mockk()
    private val responseRid: Response<RouteRidResult> = mockk()
    private val responseList: GeneralResult = mockk()
    private val call: Call<RouteRidResult> = mockk()

    private lateinit var routeService: RouteService

    @Before
    fun setUp() {
        routeService = RouteService(retrofitApi, converter)
        mockkObject(NetworkUtil)
    }

    @Test
    fun testGetRouteListRequestId_isSuccessful_true() {

        every {
            retrofitApi.getRouters(
                date = TRAIN.dateStart,
                number = TRAIN.trainNumber
            )
        } returns call

        every { call.executeAndExceptionChek() } returns responseRid

        every { responseRid.isSuccessful } returns true
        every { responseRid.body() } returns ROUTE_RID_RESULT

        val rid = routeService.getRouteListRequestId(TRAIN)

        assert(rid == ROUTE_RID_RESULT.requestId)

    }

    @Test
    fun testGetRouteListRequestId_isSuccessful_false() {

        every {
            retrofitApi.getRouters(
                date = TRAIN.dateStart,
                number = TRAIN.trainNumber
            )
        } returns call

        every { call.executeAndExceptionChek() } returns responseRid

        every { responseRid.isSuccessful } returns false

        val rid = routeService.getRouteListRequestId(TRAIN)

        assert(rid == null)
        verify(exactly = 0) { responseRid.body() }

    }


    @Test
    fun testGetRoutesList() {

        every {
            NetworkUtil.getResponseFromId(ROUTE_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.response?.error } returns null
        every { responseList.response?.routes } returns LIST_TRAIN_STOP_DTO
        every { converter.convert(LIST_TRAIN_STOP_DTO) } returns LIST_TRAIN_STOP

        val list = routeService.getRoutesList(RID)

        assert(list == LIST_TRAIN_STOP)

    }

    @Test
    fun testGetRoutesList_null() {

        every {
            NetworkUtil.getResponseFromId(ROUTE_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.response?.error } returns null
        every { responseList.response?.routes } returns null

        val list = routeService.getRoutesList(RID)

        assert(list == emptyList<TrainStop>())

    }

    @Test
    fun testGetRoutesList_throwServerSendError() {

        every {
            NetworkUtil.getResponseFromId(ROUTE_LAYER_ID,
                RID,
                retrofitApi)
        } returns responseList

        every { responseList.response?.error } returns ROUTES_ERROR

        assertFailsWith<ServerSendError> { routeService.getRoutesList(RID) }

    }


}