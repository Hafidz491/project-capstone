package com.dicoding.picodiploma.sigfood.UI

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.sigfood.UI.category.CategoryViewModel
import com.dicoding.picodiploma.sigfood.UI.detail.DetailUserViewModel
import com.dicoding.picodiploma.sigfood.UI.login.LoginViewModel
import com.dicoding.picodiploma.sigfood.model.UserPreference

class ViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(mApplication) as T
        }
         throw IllegalAccessException("Unknown ViewModel class: "+ modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}