package com.crostage.trainchecker.data.network.adapter

import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN_STOP_DTO
import com.crostage.trainchecker.data.model.rout.Response
import com.crostage.trainchecker.data.model.rout.RoutesError
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RouteResponseDeserializerTest {


    lateinit var deserializer: RouteResponseDeserializer

    @Before
    fun setUp() {
        deserializer = RouteResponseDeserializer()
    }

    @Test
    fun testDeserialize_error() {
        val gson = GsonBuilder().registerTypeAdapter(
            Response::class.java, deserializer
        ).create()

        val result = gson.fromJson(RESPONSE_ERROR_STRING, Response::class.java)

        assert(result == RESPONSE_ERROR)
    }

    @Test
    fun testDeserialize() {
        val gson = GsonBuilder().registerTypeAdapter(
            Response::class.java, deserializer
        ).create()

        val result = gson.fromJson(RESPONSE_ROUTE_STRING, Response::class.java)

        assert(result == RESPONSE_ROUTE)
    }

    @Test
    fun testDeserialize_empty() {
        val gson = GsonBuilder().registerTypeAdapter(
            Response::class.java, deserializer
        ).create()

        val result = gson.fromJson(RESPONSE_EMPTY_STRING, Response::class.java)

        assert(result == RESPONSE_EMPTY)
    }


    companion object {
        private const val RESPONSE_ROUTE_STRING = "{\"Routes\":{\"Stop\":" +
                "[{\"Code\":100,\"DepTime\":\"14:20\",\"Distance\":200,\"Station\":\"КУКУЕВО\"}]}}"

        private const val RESPONSE_ERROR_STRING = "{\"Error\":{\"content\":\"ошибочка\"}}"
        private const val RESPONSE_EMPTY_STRING = "{}"

        private val ROUTES_ERROR: RoutesError = RoutesError("ошибочка")

        val RESPONSE_ERROR: Response = Response(emptyList(), ROUTES_ERROR)
        val RESPONSE_ROUTE: Response = Response(LIST_TRAIN_STOP_DTO, null)
        val RESPONSE_EMPTY: Response = Response(emptyList(), null)
    }
}



