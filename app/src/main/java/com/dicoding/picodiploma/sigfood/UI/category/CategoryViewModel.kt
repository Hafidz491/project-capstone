package com.dicoding.picodiploma.sigfood.UI.category

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.sigfood.API.ApiConfig
import com.dicoding.picodiploma.sigfood.model.CategoryRecipeResponse
import com.dicoding.picodiploma.sigfood.model.DetailRecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel(application: Application): ViewModel() {

    private val _CategoryResponse = MutableLiveData<CategoryRecipeResponse>()
    val categoryResponse: LiveData<CategoryRecipeResponse> = _CategoryResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val detail = MutableLiveData<CategoryRecipeResponse>()


    fun setCategoryRecipe(key: String){
        _isLoading.value = true
        ApiConfig.getApiService().getCategoryRecipes(key)
            .enqueue(object : Callback<CategoryRecipeResponse> {
                override fun onResponse(
                    call: Call<CategoryRecipeResponse>,
                    response: Response<CategoryRecipeResponse>
                ) {
                    if (response.isSuccessful) {
                        detail.postValue(response.body())
                        _isLoading.value = false
                    }else{
                        Log.d("onFailure",response.message().toString())
                        _isLoading.value = true
                    }
                }

                override fun onFailure(call: Call<CategoryRecipeResponse>, t: Throwable) {
                    Log.d("onFailure",t.message.toString())
                    _isLoading.value = true
                }

            })
    }
}