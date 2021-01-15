package com.rma.marvelchallenge.ui.home.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rma.marvelchallenge.R
import com.rma.marvelchallenge.core.exception.Failure
import com.rma.marvelchallenge.core.platform.ProgressDialog
import com.rma.marvelchallenge.data.ApiClient
import com.rma.marvelchallenge.features.event.EventRepository
import com.rma.marvelchallenge.features.event.GetEvents

class EventsFragment : Fragment(), EventsMVP.View {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: EventsMVP.Presenter
    private lateinit var eventCollection: RecyclerView
    private lateinit var eventAdapter: EventItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_events, container, false)

        // Configure character item adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        eventCollection = root.findViewById(R.id.eventCollection)
        eventCollection.layoutManager = linearLayoutManager
        eventAdapter = EventItemAdapter(mutableListOf(), requireContext())
        eventCollection.adapter = eventAdapter

        progressDialog = ProgressDialog(requireContext())
        presenter = EventsPresenter(this,
            GetEvents(
                EventRepository(
                    ApiClient.getApiService()
                )
            )
        )

        presenter.fetchEvents()
        return root
    }

    override fun onResume() {
        super.onResume()
        progressDialog.attach(requireContext())
        presenter.onResume(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        progressDialog.attach(null)
        super.onDestroy()
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun showEvents(events: List<EventItem>) {
        if (events.isEmpty()){
            Toast.makeText(
                requireContext(),
                "No hay eventos por el momento",
                Toast.LENGTH_LONG
            ).show()
        }else{
            eventAdapter.setItems(events)
        }
    }

    override fun showFailure(failure: Failure) {
        Toast.makeText(
            requireContext(),
            if (failure.hasMessage) failure.message else "Error al obtener eventos",
            Toast.LENGTH_LONG
        ).show()
    }
}