package com.example.medix.ui.medication

import androidx.lifecycle.ViewModel
import com.example.medix.data.MedixRepository

class MedicationViewModel(private val medixRepository: MedixRepository): ViewModel() {

    fun getMedication(idMedication: String) = medixRepository.getMedication(idMedication)
}