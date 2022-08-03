package dev.farouk.takara.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.farouk.takara.data.db.EventRepository
import dev.farouk.takara.data.model.Event
import javax.inject.Inject

/**
 * Created by Farouk on 25/12/2020.
 */
@HiltViewModel
class EventsViewModel @Inject constructor (
    eventsRepository: EventRepository
): ViewModel() {
    // events list
    val candidates: LiveData<List<Event>> = eventsRepository.getEvents().asLiveData()
}