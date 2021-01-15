package com.rma.marvelchallenge.features.event

import android.util.Log
import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.functional.Either
import com.rma.marvelchallenge.data.ApiService
import com.rma.marvelchallenge.data.response.event.toDomain
import com.rma.marvelchallenge.domain.Event

class EventRepository(
    private val apiService: ApiService
) : IEventRepository{

    override fun getEvents(limit: Int, orderBy: String): Either<Failure, List<Event>> {
        try {
            val result = apiService.getEvents(limit, orderBy).execute()
            if (result.isSuccessful){
                if (result.body()?.code == 200){
                    val events = mutableListOf<Event>()
                    for (eventResponse in result.body()?.data?.results ?: mutableListOf()){
                        val event = eventResponse.toDomain()
                        events.add(event)
                    }
                    return Either.Right(events)
                }
            }
            return Either.Left(Failure.ServerError)
        }catch (e: Throwable){
            Log.e(TAG, "getEvents: ${e.message ?: ""}", e)
            return Either.Left(Failure.ServerError)
        }
    }

    companion object{
        private var TAG: String = EventRepository::class.java.name
    }
}