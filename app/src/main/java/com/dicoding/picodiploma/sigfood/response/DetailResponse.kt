package com.dicoding.picodiploma.sigfood.response

import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("method") val method : String,
    @SerializedName("status") val status : Boolean,
    @SerializedName("results") val results : List<DetailRecipe>
)
