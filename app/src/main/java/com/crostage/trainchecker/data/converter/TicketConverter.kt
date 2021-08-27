package com.crostage.trainchecker.data.converter

import com.crostage.trainchecker.model.data.train.TicketDto
import com.crostage.trainchecker.model.domain.Ticket
import javax.inject.Inject


class TicketConverter @Inject constructor() :
    IConverter<@JvmSuppressWildcards List<TicketDto>, @JvmSuppressWildcards List<Ticket>> {

    override fun convert(input: List<TicketDto>): List<Ticket> {
        val out = mutableListOf<Ticket>()
        for (t in input) {
            out.add(Ticket(
                t.freeSeats,
                t.tariff,
                t.type,
                t.typeLoc
            ))
        }
        return out
    }

    override fun revers(input: List<Ticket>): List<TicketDto> {
        val out = mutableListOf<TicketDto>()
        for (t in input) {
            out.add(TicketDto(
                t.freeSeats,
                t.tariff,
                t.type,
                t.typeLoc
            ))
        }
        return out
    }


}