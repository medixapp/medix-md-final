package com.example.medix.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medix.data.MedixRepository
import com.example.medix.di.Injection
import com.example.medix.ui.detailarticle.DetailArticleViewModel
import com.example.medix.ui.disease.DiseaseViewModel
import com.example.medix.ui.editprofile.EditProfileViewModel
import com.example.medix.ui.login.LoginViewModel
import com.example.medix.ui.main.MainViewModel
import com.example.medix.ui.medication.MedicationViewModel
import com.example.medix.ui.nav.article.ArticleViewModel
import com.example.medix.ui.nav.chat.ChatViewModel
import com.example.medix.ui.nav.home.HomeViewModel
import com.example.medix.ui.nav.profile.ProfileViewModel
import com.example.medix.ui.signup.SignUpViewModel

class ViewModelFactory(private val repository: MedixRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DiseaseViewModel::class.java) -> {
                DiseaseViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailArticleViewModel::class.java) -> {
                DetailArticleViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MedicationViewModel::class.java) -> {
                MedicationViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
                EditProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}