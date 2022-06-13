package com.dicoding.picodiploma.sigfood.response

import com.dicoding.picodiploma.sigfood.model.Recipes
import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("method") var method : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("results") var results : MutableList<Recipes>
)
