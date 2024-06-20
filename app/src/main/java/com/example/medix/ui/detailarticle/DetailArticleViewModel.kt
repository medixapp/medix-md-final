package com.example.medix.ui.detailarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.medix.data.MedixRepository
import com.example.medix.data.pref.UserModel

class DetailArticleViewModel(private val medixRepository: MedixRepository): ViewModel() {
    fun getDetailArticles(idArticle: Number) = medixRepository.getDetailArticles(idArticle)

    fun getSession(): LiveData<UserModel> {
        return medixRepository.getSession().asLiveData()
    }
}