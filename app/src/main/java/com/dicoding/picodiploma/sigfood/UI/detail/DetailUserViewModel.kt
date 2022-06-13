package com.dicoding.picodiploma.sigfood.UI.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.sigfood.API.ApiConfig
import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.dicoding.picodiploma.sigfood.model.DetailRecipeResponse
import com.dicoding.picodiploma.sigfood.response.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): ViewModel() {

    private val _detailResponse = MutableLiveData<DetailRecipeResponse>()
    val detailResponse: LiveData<DetailRecipeResponse> = _detailResponse

    private val detail = MutableLiveData<DetailRecipeResponse>()
    private val result = MutableLiveData<List<DetailRecipe>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading


    fun setRecipeData(key: String){
        _isLoading.value = true
        ApiConfig.getApiService().getDetailRecipe(key)
            .enqueue(object :Callback<DetailRecipeResponse>{
                override fun onResponse(
                    call: Call<DetailRecipeResponse>,
                    response: Response<DetailRecipeResponse>
                ) {
                   if (response.isSuccessful){
                       detail.postValue(response.body())
                       _detailResponse.postValue(response.body())
                       _isLoading.value = false
                   }else{
                       Log.d("onFailure",response.message().toString())
                       _isLoading.value = true
                   }
                }

                override fun onFailure(call: Call<DetailRecipeResponse>, t: Throwable) {
                    Log.d("onFailure",t.message.toString())
                    _isLoading.value = true
                }

            })
    }

//    fun getRecipeData(): LiveData<DetailResponse>{
//        return
//    }

    fun getDetailResult(): LiveData<List<DetailRecipe>>{
        return result
    }

}