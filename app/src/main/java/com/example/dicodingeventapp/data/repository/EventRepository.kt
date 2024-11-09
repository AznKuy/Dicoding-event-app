package com.example.dicodingeventapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dicodingeventapp.data.remote.response.EventResponse
import com.example.dicodingeventapp.data.remote.response.ListEventsItem
import com.example.dicodingeventapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository {


    fun fetchEvents(
        upcomingEvent: MutableLiveData<List<ListEventsItem>>,
        isLoading: MutableLiveData<Boolean>
    ) {
        isLoading.postValue(true)

        val client = ApiConfig.getApiService().getEvent(1)
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                isLoading.postValue(false)

                if (response.isSuccessful) {
                    upcomingEvent.postValue(response.body()?.listEvents?.filterNotNull())
                    Log.d("EventViewModel", "onResponse: ${response.body()?.listEvents}")
                } else {
                    Log.e("EventViewModel", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                isLoading.postValue(false)
                Log.e("EventViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun fetchFinishedEvents(
        finishedEvent: MutableLiveData<List<ListEventsItem>>,
        isLoading: MutableLiveData<Boolean>
    ) {
        isLoading.postValue(true)


        val client = ApiConfig.getApiService().getFinishedEvent(0)
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                isLoading.postValue(false)

                if (response.isSuccessful) {
                    val finishedEvents = response.body()?.listEvents?.filterNotNull()
                    finishedEvent.postValue(finishedEvents)
                    Log.d("EventViewModel", "Finished events fetched: $finishedEvents")
                } else {
                    Log.e("EventViewModel", "Failed to fetch finished events: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                isLoading.postValue(false)
                Log.e("EventViewModel", "onFailure: ${t.message}")
            }
        })
    }
}