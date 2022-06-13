package com.dicoding.picodiploma.sigfood.model

import com.google.gson.annotations.SerializedName

data class CategoryRecipeResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: List<ResultItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class ResultItem(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null
)
