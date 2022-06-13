package com.dicoding.picodiploma.sigfood.model

import com.google.gson.annotations.SerializedName

data class CategoryRecipe(
    @SerializedName("category") val category : String,
    @SerializedName("key") val key : String
)
