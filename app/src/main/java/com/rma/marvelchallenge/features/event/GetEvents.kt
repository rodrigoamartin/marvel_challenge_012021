package com.rma.marvelchallenge.features.event

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.core.interactor.UseCase
import com.rma.marvelchallenge.domain.Event

class GetEvents (
    private val eventRepository: IEventRepository
): UseCase<List<Event>, GetEvents.Params>(){

    data class Params(
        val orderBy: EventsOrderBy = EventsOrderBy.START_DATE_AS,
        val limit: Int = 25
    )

    override suspend fun run(params: Params): Either<Failure, List<Event>> {
        val orderBy: String = when(params.orderBy){
            EventsOrderBy.START_DATE_AS -> "startDate"
            EventsOrderBy.START_DATE_DESC -> "-startDate"
        }
        return eventRepository.getEvents(orderBy = orderBy, limit = params.limit)
    }
}