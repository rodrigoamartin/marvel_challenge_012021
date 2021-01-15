package com.rma.marvelchallenge.ui.home.events

import com.rma.marvelchallenge.core.functional.onFailure
import com.rma.marvelchallenge.core.functional.onSuccess
import com.rma.marvelchallenge.domain.Event
import com.rma.marvelchallenge.features.event.GetEvents

class EventsPresenter(
    private var view: EventsMVP.View? = null,
    private val getEvents: GetEvents
) : EventsMVP.Presenter{
    override fun onResume(view: EventsMVP.View) {
        this.view = view
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun fetchEvents() {
        view?.showProgress()
        getEvents(GetEvents.Params()){ result ->
            result.onSuccess {
                view?.hideProgress()
                view?.showEvents(mapList(it))
            }
            result.onFailure {
                view?.hideProgress()
                view?.showFailure(it)
            }
        }
    }

    private fun mapList(it: List<Event>): List<EventItem> {
        val eventItems = mutableListOf<EventItem>()
        for (event in it){
            eventItems.add(EventItem(
                id = event.id,
                iconUrl = event.iconUrl,
                name = event.name,
                startDate = event.startDate,
                endDate = event.endDate,
                comics = event.comics,
                isExpanded = false
            ))
        }
        return eventItems
    }
}