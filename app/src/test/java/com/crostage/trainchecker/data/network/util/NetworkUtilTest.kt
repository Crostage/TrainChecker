package com.crostage.trainchecker.data.network.util

import com.crostage.trainchecker.data.model.GeneralResult
import com.crostage.trainchecker.data.network.ApiRequests
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.RID
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.executeAndExceptionChek
import com.crostage.trainchecker.data.network.util.NetworkUtil.Companion.getResponseFromId
import com.crostage.trainchecker.utils.Constant.Companion.TRAIN_LAYER_ID
import com.crostage.trainchecker.utils.Error401
import com.crostage.trainchecker.utils.Error404
import com.crostage.trainchecker.utils.ErrorConnections
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
class NetworkUtilTest {

    private val call: Call<GeneralResult> = mockk()
    private val generalResponse: Response<GeneralResult> = mockk()
    private val retrofitApi: ApiRequests = mockk()
    private val result: GeneralResult = mockk()

    @Before
    fun setUp() {
        mockkObject(NetworkUtil)
    }


    @Test
    fun testGetResponseFromId_isSuccessful_true() {
        every {
            retrofitApi.getResultFromRid(
                layerId = TRAIN_LAYER_ID,
                requestId = RID
            )
        } returns call
        every { call.executeAndExceptionChek() } returns generalResponse
        every { generalResponse.isSuccessful } returns true
        every { generalResponse.body() } returns result

        val data = getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)

        assert(data == result)
    }

    @Test
    fun testGetResponseFromId_isSuccessful_false() {
        every {
            retrofitApi.getResultFromRid(
                layerId = TRAIN_LAYER_ID,
                requestId = RID
            )
        } returns call
        every { call.executeAndExceptionChek() } returns generalResponse
        every { generalResponse.isSuccessful } returns false


        val data = getResponseFromId(TRAIN_LAYER_ID, RID, retrofitApi)

        assert(data == null)
        verify(exactly = 0) { generalResponse.body() }
    }


    @Test
    fun testExecuteAndExceptionChek_code_200() {

        every { call.execute() } returns generalResponse
        every { generalResponse.code() } returns 200

        val response = call.executeAndExceptionChek()

        assert(response == generalResponse)

    }

    @Test
    fun testExecuteAndExceptionChek_code_401() {

        every { call.execute() } returns generalResponse
        every { generalResponse.code() } returns 401

        assertFailsWith<Error401> { call.executeAndExceptionChek() }
    }

    @Test
    fun testExecuteAndExceptionChek_code_404() {

        every { call.execute() } returns generalResponse
        every { generalResponse.code() } returns 404

        assertFailsWith<Error404> { call.executeAndExceptionChek() }
    }

    @Test
    fun testExecuteAndExceptionChek_ServerSendError() {

        every { call.execute() } throws ServerSendError()

        assertFailsWith<ServerSendError> { call.executeAndExceptionChek() }
        verify(exactly = 0) { generalResponse.code() }
    }

    @Test
    fun testExecuteAndExceptionChek_Exception() {

        every { call.execute() } throws Exception()

        assertFailsWith<ErrorConnections> { call.executeAndExceptionChek() }
        verify(exactly = 0) { generalResponse.code() }

    }

}