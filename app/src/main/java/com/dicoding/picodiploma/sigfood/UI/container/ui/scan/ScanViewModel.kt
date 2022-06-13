package com.dicoding.picodiploma.sigfood.UI.container.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScanViewModel : ViewModel() {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _Isloading = MutableLiveData<Boolean>()
    val Isloading: LiveData<Boolean> = _Isloading


    fun checkToken() {
        _token.value = "INiTokeNYA"
    }
}