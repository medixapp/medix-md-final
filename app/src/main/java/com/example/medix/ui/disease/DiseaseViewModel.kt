package com.example.medix.ui.disease

import androidx.lifecycle.ViewModel
import com.example.medix.data.MedixRepository

class DiseaseViewModel(private val medixRepository: MedixRepository): ViewModel() {
    fun predictEmbedding(text: String) = medixRepository.predictEmbedding(text)
}