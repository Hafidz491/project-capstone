package com.dicoding.picodiploma.sigfood.model

import com.google.gson.annotations.SerializedName

data class DetailRecipe(
    @SerializedName("title") val name : String,
    @SerializedName("thumb") val thumbnail : String,
    @SerializedName("servings") val servings : String,
    @SerializedName("times") val times : String,
    @SerializedName("dificulty") val dificulty : String,
    @SerializedName("author") val author : AuthorRecipe,
    @SerializedName("desc") val desc : String,
    @SerializedName("ingredient") val ingredients : MutableList<String>,
    @SerializedName("step") val step : MutableList<String>
)
