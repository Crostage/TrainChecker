package com.crostage.trainchecker.data.db.converter

import com.crostage.trainchecker.ConstForTest.Companion.JSON_TICKET
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TICKET
import com.crostage.trainchecker.domain.model.Ticket
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TicketListConverterTest {

    private val gson: Gson = mockk()
    val converter = TicketListConverter()

    @Test
    fun testListToJson() {
        every { gson.toJson(LIST_TICKET) } returns JSON_TICKET
        val json = converter.listToJson(LIST_TICKET)
        assertEquals(json, JSON_TICKET)
    }

    @Test
    fun testJsonToList() {
        every {
            gson.fromJson(JSON_TICKET, Array<Ticket>::class.java).toList()
        } returns LIST_TICKET
        val json = converter.jsonToList(JSON_TICKET)
        assertEquals(json, LIST_TICKET)
    }

}