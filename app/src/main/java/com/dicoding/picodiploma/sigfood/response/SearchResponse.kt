package com.dicoding.picodiploma.sigfood.response

import com.dicoding.picodiploma.sigfood.model.SearchRecipe
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("method") var method : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("results") var results : MutableList<SearchRecipe>
)
