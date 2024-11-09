package com.example.dicodingeventapp.ui.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingeventapp.data.repository.EventRepository
import com.example.dicodingeventapp.data.remote.response.ListEventsItem

class UpcomingViewModel : ViewModel() {

    private val repository = EventRepository()

    private val _upcomingEvent = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvent: MutableLiveData<List<ListEventsItem>> get() = _upcomingEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    init {
        fetchEvents()
    }

    private fun fetchEvents(){
        repository.fetchEvents(_upcomingEvent, _isLoading)
    }
}