package com.example.dicodingeventapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEvent::class], version = 2)
abstract class EventFavoriteRoomDatabase : RoomDatabase() {
    abstract fun eventFavoriteDao(): EventFavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: EventFavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): EventFavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(EventFavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EventFavoriteRoomDatabase::class.java,
                        "event_favorite_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as EventFavoriteRoomDatabase
        }
    }
}