package com.rma.marvelchallenge.ui.home.events

import com.rma.marvelchallenge.core.exception.Failure

interface EventsMVP {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun showEvents(events: List<EventItem>)
        fun showFailure(failure: Failure)
    }

    interface Presenter{
        fun onResume(view: View)
        fun onDestroy()
        fun fetchEvents()
    }
}