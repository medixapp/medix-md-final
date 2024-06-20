package com.example.medix.data.api.response

import com.google.gson.annotations.SerializedName

data class ChatbotResponse(

	@field:SerializedName("answer")
	val answer: String? = null
)
