package com.crostage.trainchecker.data.network

import io.mockk.*
import okhttp3.Interceptor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import java.io.File
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class RetrofitBuilderTest {


    private val file: File = mockk()
    private val interceptor: Interceptor = mockk()
    private val retrofit: Retrofit = mockk()
    private val api: ApiRequests = mockk()
    private val retrofitBuilder = spyk(RetrofitBuilder(interceptor), recordPrivateCalls = true)

    @Before
    fun setUp() {
    }

    @Test
    fun testGetApi() {
        every { retrofitBuilder["getRetrofit"](file) } returns retrofit
        every { retrofit.create(ApiRequests::class.java) } returns api

        val a = retrofitBuilder.getApi(file)

        verify {
            retrofitBuilder["getRetrofit"](file)
            retrofit.create(ApiRequests::class.java)
        }
        assertEquals(a, api)
    }


}