package com.crostage.trainchecker.data.network.services

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.RID
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.model.train.ErrorMessage
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.data.model.train.TrainResponse
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.util.NetworkUtil
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.utils.Constant.Companion.TRAIN_LAYER_ID
import com.crostage.trainchecker.utils.ServerSendError
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(MockitoJUnitRunner::class)
class TrainServiceTest {

    private val retrofitApi: ApiRequests = mockk()
    private val converter: IConverter<List<TrainEntity>, List<Train>> = mockk()
    private val response: Response<GeneralResult> = mockk()
    private val body: GeneralResult = mockk()
    private val trainResponseList: List<TrainResponse> = mockk()
    private val errorList: List<ErrorMessage> = mockk()
    private val call: Call<GeneralResult> = mockk()
    private val result: GeneralResult = mockk()
    private lateinit var trainService: TrainService

    @Before
    fun setUp() {
        trainService = TrainService(retrofitApi, converter)
        mockkObject(NetworkUtil)
    }

    @Test
    fun testGetTrainListRid_isSuccessful_true() {
        every {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        } returns call
        every { call.executeAndExceptionChek() } returns response
        every { response.isSuccessful } returns true
        every { response.body() } returns body
        every { body.trainResponse } returns trainResponseList
        every { trainResponseList[0].msgList } returns errorList
        every { errorList[0].message } returns null
        every { body.requestId } returns RID

        val rid = trainService.getTrainListRid(
            codeFrom = TRAIN.codeStationFrom,
            codeTo = TRAIN.codeStationTo,
            date = TRAIN.dateStart
        )

        verifySequence {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
        assertEquals(rid, RID)

    }

    @Test
    fun testGetTrainListRid_isSuccessful_true_null() {
        every {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        } returns call
        every { call.executeAndExceptionChek() } returns response
        every { response.isSuccessful } returns true
        every { response.body() } returns body
        every { body.trainResponse } returns trainResponseList
        every { trainResponseList[0].msgList } returns errorList
        every { errorList[0].message } returns null
        every { body.requestId } returns null

        val rid = trainService.getTrainListRid(
            codeFrom = TRAIN.codeStationFrom,
            codeTo = TRAIN.codeStationTo,
            date = TRAIN.dateStart
        )

        verify {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
        assertEquals(rid, null)

    }


    @Test
    fun testGetTrainListRid_throw_ServerSendError() {

        every {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        } returns call
        every { call.executeAndExceptionChek() } returns response
        every { response.isSuccessful } returns true
        every { response.body() } returns body
        every { body.trainResponse } returns trainResponseList
        every { trainResponseList[0].msgList } returns errorList
        every { errorList[0].message } returns "error"


        assertFailsWith<ServerSendError> {
            trainService.getTrainListRid(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
        verify {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
    }


    @Test
    fun testGetTrainListRid_throw_exception() {
        every {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        } returns call
        every { call.executeAndExceptionChek() } returns response
        every { response.isSuccessful } returns true
        every { response.body() } returns body
        every { body.trainResponse } returns trainResponseList
        every { trainResponseList[0] } throws IndexOutOfBoundsException()

        assertFailsWith<ServerSendError> {
            trainService.getTrainListRid(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
        verify {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }

    }


    @Test
    fun testGetTrainListRid_isSuccessful_false() {
        every {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        } returns call
        every { call.executeAndExceptionChek() } returns response
        every { response.isSuccessful } returns false

        val rid = trainService.getTrainListRid(
            codeFrom = TRAIN.codeStationFrom,
            codeTo = TRAIN.codeStationTo,
            date = TRAIN.dateStart
        )

        verify {
            retrofitApi.getTrains(
                codeFrom = TRAIN.codeStationFrom,
                codeTo = TRAIN.codeStationTo,
                date = TRAIN.dateStart
            )
        }
        assertEquals(rid, null)
    }


    @Test
    fun testGetTrainList() {
        every {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        } returns result
        every { result.trainResponse } returns trainResponseList
        every { trainResponseList[0].list } returns LIST_TRAIN_ENTITY
        every { converter.convert(LIST_TRAIN_ENTITY) } returns LIST_TRAIN

        val list = trainService.getTrainList(RID)

        verify {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        }
        assertEquals(list, LIST_TRAIN)
    }


    @Test
    fun testGetTrainList_throw_exception() {
        every {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        } returns result
        every { result.trainResponse } returns trainResponseList
        every { trainResponseList[0] } throws IndexOutOfBoundsException()

        assertFailsWith<ServerSendError> { trainService.getTrainList(RID) }
        verify {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        }
    }


    @Test
    fun testGetTrainList_null() {
        every {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        } returns result
        every { result.trainResponse } returns null

        val list = trainService.getTrainList(RID)

        verify {
            NetworkUtil.getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)
        }
        assertEquals(list, emptyList())
    }

}