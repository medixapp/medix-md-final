package com.example.medix.data.api.response

import com.google.gson.annotations.SerializedName

data class MedicationResponse(

	@field:SerializedName("data")
	val data: DataMedication? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataMedication(

	@field:SerializedName("info")
	val info: List<InfoItem?>? = emptyList()
)

data class InfoItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("medicine")
	val medicine: String? = null
)
