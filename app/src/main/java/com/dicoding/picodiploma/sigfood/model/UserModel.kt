package com.dicoding.picodiploma.sigfood.model

data class UserModel(
    val userId: String,
    val name: String,
    val token : String,
    val isLogin: Boolean
)
