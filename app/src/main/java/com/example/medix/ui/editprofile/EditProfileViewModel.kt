package com.example.medix.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel
import okhttp3.MultipartBody
import okhttp3.RequestBody

class EditProfileViewModel(private val medixRepository: MedixRepository): ViewModel() {

    fun getUpdateProfile(token: String, description: RequestBody, file: MultipartBody.Part) = medixRepository.getUpdateProfile(token, description, file)
    fun getSession(): LiveData<UserModel> {
        return medixRepository.getSession().asLiveData()
    }
}