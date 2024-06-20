package com.example.medix.ui.nav.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medix.data.MedixRepository
import com.example.medix.data.api.ApiConfig
import com.example.medix.data.api.response.ChatbotResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel(private val medixRepository: MedixRepository): ViewModel() {

    private val _chatbot = MutableLiveData<ChatbotResponse>()
    val chatbot: LiveData<ChatbotResponse> = _chatbot

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "ChatViewModel"
    }

    fun postReview(review: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService2().chatbot(review)
        client.enqueue(object : Callback<ChatbotResponse> {
            override fun onResponse(call: Call<ChatbotResponse>, response: Response<ChatbotResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _chatbot.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ChatbotResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
                    })
            }
}