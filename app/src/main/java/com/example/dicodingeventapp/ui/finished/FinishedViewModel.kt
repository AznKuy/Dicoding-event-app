package com.example.dicodingeventapp.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingeventapp.data.repository.EventRepository
import com.example.dicodingeventapp.data.remote.response.ListEventsItem


class FinishedViewModel : ViewModel() {

    private val repository = EventRepository()

    private val _finishedEvent = MutableLiveData<List<ListEventsItem>>()
    val finishedEvent: LiveData<List<ListEventsItem>> get() = _finishedEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchFinishedEvents()
    }

    private fun fetchFinishedEvents() {
        repository.fetchFinishedEvents(_finishedEvent, _isLoading)
    }
}