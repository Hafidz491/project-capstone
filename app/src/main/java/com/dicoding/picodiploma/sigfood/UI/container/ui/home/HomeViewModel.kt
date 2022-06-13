package com.dicoding.picodiploma.sigfood.UI.container.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.sigfood.API.ApiConfig
import com.dicoding.picodiploma.sigfood.UI.adapter.ItemAdapter
import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.model.SearchRecipe
import com.dicoding.picodiploma.sigfood.model.UserModel
import com.dicoding.picodiploma.sigfood.response.DetailResponse
import com.dicoding.picodiploma.sigfood.response.RecipesResponse
import com.dicoding.picodiploma.sigfood.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _ApiMessage = MutableLiveData<String>()
    val ApiMessage : LiveData<String> = _ApiMessage

    private val _listRecipe = MutableLiveData<RecipesResponse>()
    val listRecipe: LiveData<RecipesResponse> = _listRecipe

    fun getListRecipe() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllRecipes()
        client.enqueue(object: Callback<RecipesResponse> {
            override fun onResponse(
                call: Call<RecipesResponse>,
                response: Response<RecipesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _listRecipe.value = response.body()

                }
            }
            override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                _isLoading.value = false
                _ApiMessage.value = t.message
                Log.e("HomeFragment", "onFailure: ${t.message}")
            }

        })
    }
}



