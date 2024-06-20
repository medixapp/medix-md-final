package com.example.medix.data.pref

data class UserModel(
    val session: String,
    val token: String,
    val isLogin: Boolean = false

)