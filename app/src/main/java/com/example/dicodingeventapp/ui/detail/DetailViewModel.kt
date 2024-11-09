package com.example.dicodingeventapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingeventapp.data.remote.response.DetailEventResponse
import com.example.dicodingeventapp.data.remote.response.Event
import com.example.dicodingeventapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _eventDetail = MutableLiveData<Event>()
    val eventDetail: LiveData<Event> get() = _eventDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getEventById(id: Int) {
        _isLoading.postValue(true)

        val client = ApiConfig.getApiService().getEventDetail(id.toString())
        client.enqueue(object : Callback<DetailEventResponse> {
            override fun onResponse(call: Call<DetailEventResponse>, response: Response<DetailEventResponse>) {
                if (response.isSuccessful) {
                    Log.d("EventViewModel", "Received event: ${response.body()}")
                    _eventDetail.postValue(response.body()?.event)
                } else {
                    Log.e("EventViewModel", "Failed to fetch event: ${response.errorBody()}")
                }
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<DetailEventResponse>, t: Throwable) {
                Log.e("EventViewModel", "onFailure: ${t.message}")
                _isLoading.postValue(false)
            }
        })
    }
}