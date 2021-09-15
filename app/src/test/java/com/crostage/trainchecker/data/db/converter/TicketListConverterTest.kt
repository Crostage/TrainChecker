package com.crostage.trainchecker.data.db.converter

import com.crostage.trainchecker.ConstForTest.Companion.JSON_TICKET
import com.crostage.trainchecker.ConstForTest.Companion.TICKET
import com.crostage.trainchecker.domain.model.Ticket
import com.google.gson.Gson
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TicketListConverterTest {

    private val gson: Gson = mockk()
    val converter = TicketListConverter()

    @Test
    fun testListToJson() {
        every { gson.toJson(listOf(TICKET)) } returns JSON_TICKET
        val json = converter.listToJson(listOf(TICKET))
        assert(json == JSON_TICKET)
    }

    @Test
    fun testJsonToList() {
        every {
            gson.fromJson(JSON_TICKET, Array<Ticket>::class.java).toList()
        } returns listOf(TICKET)
        val json = converter.jsonToList(JSON_TICKET)
        assert(json == listOf(TICKET))
    }

}