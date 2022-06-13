package com.dicoding.picodiploma.sigfood.API

import com.dicoding.picodiploma.sigfood.model.CategoryRecipeResponse
import com.dicoding.picodiploma.sigfood.model.DetailRecipeResponse
import com.dicoding.picodiploma.sigfood.response.CategoryResponse
import com.dicoding.picodiploma.sigfood.response.DetailResponse
import com.dicoding.picodiploma.sigfood.response.RecipesResponse
import com.dicoding.picodiploma.sigfood.response.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIservice {

        @GET("/api/recipes-length/?limit=5")
        fun getAllRecipes() : Call<RecipesResponse>

        @GET("/api/recipes/{key}")
        fun getDetailRecipe(@Path("key") key: String) : Call<DetailRecipeResponse>

        @GET("/api/search/")
        fun getSearchRecipe(@Query("q") query : String) : Call<SearchResponse>

        @GET("/api/categorys/recipes")
        fun getCategorys() : Call<CategoryResponse>

        @GET("/api/category/recipes/{key}")
        fun getCategoryRecipes(@Path("key") key: String) : Call<CategoryRecipeResponse>

}