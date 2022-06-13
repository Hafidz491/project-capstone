package com.dicoding.picodiploma.sigfood.UI.signup

import android.content.Context
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.response.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isNameEmpty = MutableLiveData<Boolean>()
    val isNameEmpty: LiveData<Boolean> = _isNameEmpty

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _isEmailEmpty = MutableLiveData<Boolean>()
    val isEmailEmpty: LiveData<Boolean> = _isEmailEmpty

    private val _isPasswordEmpty = MutableLiveData<Boolean>()
    val isPasswordEmpty: LiveData<Boolean> = _isPasswordEmpty

    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _ApiMessage = MutableLiveData<String>()
    val ApiMessage: LiveData<String> = _ApiMessage

    private fun passwordValidation(pass: String): Boolean {
        return pass.length >= 6
    }

    private fun emailValidation(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun formNotEmpty(name: String, password: String, email: String): Boolean {
        var isNotEmpty = true

        if (name.isEmpty()) {
            _isNameEmpty.value = true
            isNotEmpty = false
        } else if (password.isEmpty()) {
            _isPasswordEmpty.value = true
            isNotEmpty = false
        } else if (email.isEmpty()) {
            _isEmailEmpty.value = true
            isNotEmpty = false
        }

        return isNotEmpty
    }

    fun formValidation(name: String, pass: String, email: String): Boolean {
        var isValid = true

        if (formNotEmpty(name, pass, email)) {
            if (!emailValidation(email)) {
                _isEmailValid.value = false
                isValid = false
            } else if (!passwordValidation(pass)) {
                _isPasswordValid.value = false
                isValid = false
            }
        } else {
            isValid = false
        }

        return isValid
    }

}