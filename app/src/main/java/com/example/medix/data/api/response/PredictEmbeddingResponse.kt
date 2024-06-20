package com.example.medix.data.api.response

import com.google.gson.annotations.SerializedName

data class PredictEmbeddingResponse(

	@field:SerializedName("data")
	val data: List<DataItemPredict?>? = emptyList(),

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemPredict(

	@field:SerializedName("confidence")
	val confidence: Double,

	@field:SerializedName("penyebab")
	val penyebab: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
)
