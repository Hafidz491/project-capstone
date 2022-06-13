package com.dicoding.picodiploma.sigfood.UI.container.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.sigfood.API.ApiConfig
import com.dicoding.picodiploma.sigfood.model.SearchRecipe
import com.dicoding.picodiploma.sigfood.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _ApiMessage = MutableLiveData<String>()
    val ApiMessage : LiveData<String> = _ApiMessage

    val searchRecipe = MutableLiveData<List<SearchRecipe>>()

    fun getSearchRecipe(): LiveData<List<SearchRecipe>> {
        return searchRecipe
    }

    fun setSearchRecipe(query: String) {
        _isLoading.value = true
        ApiConfig.getApiService()
            .getSearchRecipe(query)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful){
                        searchRecipe.postValue(response.body()?.results)
                        _isLoading.value = false
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                    _isLoading.value = true
                    _ApiMessage.value = t.message
                }

            })
    }
}