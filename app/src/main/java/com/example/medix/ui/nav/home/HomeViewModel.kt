package com.example.medix.ui.nav.home

import androidx.lifecycle.ViewModel
import com.example.medix.data.MedixRepository

class HomeViewModel(private val medixRepository: MedixRepository) : ViewModel() {

    fun predictEmbedding(text: String) = medixRepository.predictEmbedding(text)
}