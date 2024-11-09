package com.example.dicodingeventapp.data.remote.retrofit

import com.example.dicodingeventapp.data.remote.response.DetailEventResponse
import com.example.dicodingeventapp.data.remote.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("events")
    fun getEvent(
        @Query("active") active: Int
    ): Call<EventResponse>

    @GET("events")
    fun getFinishedEvent(
        @Query("active") active: Int
    ): Call<EventResponse>

    @GET("events/{id}")
    fun getEventDetail(
        @Path("id") id: String
    ): Call<DetailEventResponse>
}