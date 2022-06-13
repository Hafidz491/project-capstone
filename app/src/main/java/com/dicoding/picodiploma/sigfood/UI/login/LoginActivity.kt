package com.dicoding.picodiploma.sigfood.UI.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.UI.ViewModelFactory
import com.dicoding.picodiploma.sigfood.UI.container.ContainerActivity
import com.dicoding.picodiploma.sigfood.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.sigfood.model.UserModel
import com.dicoding.picodiploma.sigfood.model.UserPreference

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "setting")

class LoginActivity : AppCompatActivity() {

    private lateinit var bindind: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var user: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        loginViewModel.isEmailValid.observe(this) {
            if (!it) bindind.emailEditText.setError(getString(R.string.invalid_email)) else bindind.emailEditText.setError(
                null
            )
        }

        setupView()
//        setupViewModel()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

//    private fun setupViewModel() {
//        loginViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(UserPreference.getInstance(dataStore))
//        )[LoginViewModel::class.java]
//
//        loginViewModel.getUser().observe(this) { user ->
//            Log.d("Berhasil Login: ", user.token)
//            this.user = user
//        }
//    }

    private fun setupAction() {
        bindind.btnLogin.setOnClickListener {
            val email = bindind.emailEditText.text.toString()
            val password = bindind.PasswordEditText.text.toString()
            when {
                email.isEmpty() -> {
                    bindind.emailEditText.error = "Masukkan Email!"
                }
                email != user.userId -> {
                    bindind.emailEditText.error = "Email yang anda masukkan salah!"
                }
                password.isEmpty() -> {
                    bindind.PasswordEditText.error = "Masukkan Password!"
                }
                password != user.token -> {
                    bindind.PasswordEditText.error = "Password yang anda masukkan salah!"
                }
                else -> {
                    loginViewModel.login(user)
                    AlertDialog.Builder(this).apply {
                        setTitle("Berhasil!")
                        setMessage("Anda Berhasil Login!")
                        setPositiveButton("Lanjut") {_, _ ->
                            val intent = Intent(context, ContainerActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                }
            }
        }
        bindind.btnSignUp.setOnClickListener {

        }
    }
}