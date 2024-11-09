package com.example.dicodingeventapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventFavoriteDao {
    @Insert
    fun addFavoriteEvent(event: FavoriteEvent)

    @Query("SELECT * FROM favorite_event ORDER BY timestamp DESC")
    fun getFavoriteEvents(): LiveData<List<FavoriteEvent>>

    @Query("SELECT * FROM favorite_event WHERE id = :id")
    fun checkFavoriteEvent(id: Int): LiveData<FavoriteEvent?>

    @Query("DELETE FROM favorite_event WHERE id = :id")
    fun removeFavoriteEvent(id: Int): Int
}