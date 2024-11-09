@file:Suppress("unused", "unused")

package com.example.dicodingeventapp.data.di

import com.example.dicodingeventapp.data.repository.EventRepository

@Suppress("unused", "unused")
object Injection {
    fun provideRepository(): EventRepository {
        return EventRepository()
    }
}