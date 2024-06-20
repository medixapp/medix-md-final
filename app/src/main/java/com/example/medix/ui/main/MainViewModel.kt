package com.example.medix.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val medixRepository: MedixRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return medixRepository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            medixRepository.logout()
        }
    }

}