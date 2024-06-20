package com.example.medix.ui.nav.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel

class ArticleViewModel(private val medixRepository: MedixRepository) : ViewModel() {
    fun getAllArticle() = medixRepository.getAllArticles()
    fun getSession(): LiveData<UserModel> {
        return medixRepository.getSession().asLiveData()
    }
}