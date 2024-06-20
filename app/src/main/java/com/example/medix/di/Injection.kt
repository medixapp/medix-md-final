package com.example.medix.di

import android.content.Context
import com.example.medix.data.MedixRepository
import com.example.medix.data.api.ApiConfig
import com.example.medix.data.pref.UserPreference
import com.example.medix.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): MedixRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
//        val user = runBlocking { pref.getUser().first() }
        return MedixRepository.getInstance(pref, apiService)
    }
    private const val BASE_URL_1 = "https://testing-medix.et.r.appspot.com/"
    private const val BASE_URL_2 = "https://backend-medix-dza5xpjrja-et.a.run.app/"
}