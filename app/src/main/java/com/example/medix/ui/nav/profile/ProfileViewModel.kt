package com.example.medix.ui.nav.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val medixRepository: MedixRepository) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            medixRepository.logout()
        }
    }
    fun getProfile(token: String) = medixRepository.getProfile(token)

    fun getSession(): LiveData<UserModel> {
        return medixRepository.getSession().asLiveData()
    }
}