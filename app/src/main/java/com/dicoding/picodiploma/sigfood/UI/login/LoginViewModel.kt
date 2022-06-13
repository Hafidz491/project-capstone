package com.dicoding.picodiploma.sigfood.UI.login

import androidx.lifecycle.*
import com.dicoding.picodiploma.sigfood.model.UserModel
import com.dicoding.picodiploma.sigfood.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid

//    fun getUser(): LiveData<UserModel> {
//        return pref.getUser().asLiveData()
//    }

    fun login(user: UserModel) {
        _isEmailValid.value = true
    }
}