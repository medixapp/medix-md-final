package com.example.medix.data.api.response

import com.google.gson.annotations.SerializedName

data class DetailArticleResponse(

	@field:SerializedName("data")
	val data: DataDetailArticle? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Article(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class DataDetailArticle(

	@field:SerializedName("article")
	val article: Article? = null
)
