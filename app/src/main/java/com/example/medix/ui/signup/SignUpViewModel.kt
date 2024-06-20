package com.example.medix.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel
import kotlinx.coroutines.launch

class SignUpViewModel(private val medixRepository: MedixRepository): ViewModel() {
    fun postUserSignUp(username: String, email: String, password: String) = medixRepository.postUserSignUp(username, email, password)
    fun postUserLogin(email: String, password: String) = medixRepository.postUserLogin(email, password)
    fun saveSession(user: UserModel){
        viewModelScope.launch {
            medixRepository.saveSession(user)
        }
    }
}