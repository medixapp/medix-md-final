package com.example.medix.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.medix.data.api.ApiService
import com.example.medix.data.api.response.ArticleResponse
import com.example.medix.data.api.response.DetailArticleResponse
import com.example.medix.data.api.response.ErrorResponse
import com.example.medix.data.api.response.LoginResponse
import com.example.medix.data.api.response.MedicationResponse
import com.example.medix.data.api.response.PredictEmbeddingResponse
import com.example.medix.data.api.response.ProfileResponse
import com.example.medix.data.api.response.RegisResponse
import com.example.medix.data.api.response.UpdateProfileResponse
import com.example.medix.data.pref.UserModel
import com.example.medix.data.pref.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class MedixRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
//    private val apiService2: ApiService2,
)  {

    fun postUserSignUp(username: String, email: String, password: String): LiveData<Result<RegisResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(username, email, password)
            emit(Result.Success(response))
        } catch (e:HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun postUserLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e:HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getAllArticles(): LiveData<Result<ArticleResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticle()
            emit(Result.Success(response))
        } catch (e: HttpException){
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getDetailArticles(idArticle: Number): LiveData<Result<DetailArticleResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailArticle(idArticle)
            emit(Result.Success(response))
        } catch (e: HttpException){
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun predictEmbedding(text: String): LiveData<Result<PredictEmbeddingResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.predictEmbedding(text)
            emit(Result.Success(response))
        } catch (e : HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getMedication(idMedication: String): LiveData<Result<MedicationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getMedication(idMedication)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getProfile(token: String): LiveData<Result<ProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProfile("Bearer $token")
            emit(Result.Success(response))
        } catch (e:HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getUpdateProfile(token: String, description: RequestBody, file: MultipartBody.Part): LiveData<Result<UpdateProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUpdateProfile("Bearer $token",  description, file)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: MedixRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
        ): MedixRepository =
            instance ?: synchronized(this) {
                instance ?: MedixRepository(userPreference, apiService)
            }.also { instance = it }
    }
}