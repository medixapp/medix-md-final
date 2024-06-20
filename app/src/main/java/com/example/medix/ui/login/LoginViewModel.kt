package com.example.medix.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val medixRepository: MedixRepository) : ViewModel() {
    fun postUserLogin(email: String, password: String) = medixRepository.postUserLogin(email, password)
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            medixRepository.saveSession(user)
        }
    }
    fun getProfile(session: String) = medixRepository.getProfile(session)

}