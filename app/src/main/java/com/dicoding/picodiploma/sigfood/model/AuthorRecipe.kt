package com.dicoding.picodiploma.sigfood.model

import com.google.gson.annotations.SerializedName

data class AuthorRecipe(
    @SerializedName("user") val author : String,
    @SerializedName("datePublished") val published : String
)
