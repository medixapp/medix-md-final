package com.example.medix.data.api

import com.example.medix.data.api.response.ArticleResponse
import com.example.medix.data.api.response.ChatbotResponse
import com.example.medix.data.api.response.DetailArticleResponse
import com.example.medix.data.api.response.LoginResponse
import com.example.medix.data.api.response.MedicationResponse
import com.example.medix.data.api.response.PredictEmbeddingResponse
import com.example.medix.data.api.response.ProfileResponse
import com.example.medix.data.api.response.RegisResponse
import com.example.medix.data.api.response.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService  {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("article")
    suspend fun getArticle(): ArticleResponse

    @GET("article/{id}")
    suspend fun getDetailArticle(
        @Path("id") id: Number
    ): DetailArticleResponse

    @FormUrlEncoded
    @POST("predict/embedding")
    suspend fun predictEmbedding(
        @Field("text") text: String,
    ): PredictEmbeddingResponse

    @GET("predict/{label}")
    suspend fun getMedication(
        @Path("label") label: String
    ): MedicationResponse

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): ProfileResponse

    @Multipart
    @POST("profile/update")
    suspend fun getUpdateProfile(
        @Header("Authorization") token : String,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
        ): UpdateProfileResponse

    @FormUrlEncoded
    @POST("answer")
    fun chatbot(
        @Field("question") question: String,
    ): Call<ChatbotResponse>
}