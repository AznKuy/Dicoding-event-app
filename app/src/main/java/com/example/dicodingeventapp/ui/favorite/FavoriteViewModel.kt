package com.example.dicodingeventapp.ui.favorite

import android.app.Application
import androidx.lifecycle.*
import com.example.dicodingeventapp.data.database.FavoriteEvent
import com.example.dicodingeventapp.data.repository.EventFavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventFavoriteRepository = EventFavoriteRepository(application)
    val favoriteEvents: LiveData<List<FavoriteEvent>> = repository.getAllFavoriteEvents()

    fun isEventFavorite(id: Int): LiveData<Boolean> {
        return repository.isEventFavorite(id)
    }

    fun addFavoriteEvent(event: FavoriteEvent) {
        viewModelScope.launch {
            repository.addFavoriteEvent(event)
        }
    }

    fun deleteFavoriteEvent(id: Int) {
        viewModelScope.launch {
            repository.removeFavoriteEvent(id)
        }
    }
}