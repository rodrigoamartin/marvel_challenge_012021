package com.rma.marvelchallenge.features.event

import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.domain.Event

interface IEventRepository {

    fun getEvents(limit: Int = 25, orderBy: String = ""): Either<Failure, List<Event>>
}