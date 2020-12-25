package dev.farouk.takara.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.farouk.takara.data.db.EventRepository
import dev.farouk.takara.data.model.Event

/**
 * Created by Farouk on 25/12/2020.
 */
class EventsViewModel @ViewModelInject constructor(
    eventsRepository: EventRepository
): ViewModel() {
    // events list
    val candidates: LiveData<List<Event>> = eventsRepository.getEvents().asLiveData()
}