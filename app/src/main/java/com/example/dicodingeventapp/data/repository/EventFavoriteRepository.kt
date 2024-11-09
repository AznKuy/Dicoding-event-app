package com.example.dicodingeventapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.dicodingeventapp.data.database.EventFavoriteDao
import com.example.dicodingeventapp.data.database.EventFavoriteRoomDatabase
import com.example.dicodingeventapp.data.database.FavoriteEvent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EventFavoriteRepository(application: Application) {
    private val mEventFavoriteDao: EventFavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        val db = EventFavoriteRoomDatabase.getDatabase(application)
        mEventFavoriteDao = db.eventFavoriteDao()
    }

    fun getAllFavoriteEvents(): LiveData<List<FavoriteEvent>> = mEventFavoriteDao.getFavoriteEvents()

    fun addFavoriteEvent(event: FavoriteEvent) {
        executorService.execute { mEventFavoriteDao.addFavoriteEvent(event) }
    }

    fun removeFavoriteEvent(id: Int) {
        executorService.execute { mEventFavoriteDao.removeFavoriteEvent(id) }
    }

    fun isEventFavorite(id: Int): LiveData<Boolean> {
        return mEventFavoriteDao.checkFavoriteEvent(id).map { favoriteEvent ->
            favoriteEvent != null
        }
    }


}