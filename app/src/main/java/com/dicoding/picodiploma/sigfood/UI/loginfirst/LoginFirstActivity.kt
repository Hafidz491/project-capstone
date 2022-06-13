package com.dicoding.picodiploma.sigfood.UI.loginfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.UI.container.ContainerActivity
import com.dicoding.picodiploma.sigfood.UI.container.ui.home.HomeFragment
import com.dicoding.picodiploma.sigfood.UI.login.LoginActivity
import com.dicoding.picodiploma.sigfood.UI.signup.SignUpActivity
import com.dicoding.picodiploma.sigfood.databinding.ActivityLoginFirstBinding

class LoginFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mHomeFragment = HomeFragment()
        val mFragmentManager = supportFragmentManager

        binding.imgBack.setOnClickListener {
            val intentToHomeFragment = Intent(this@LoginFirstActivity, ContainerActivity::class.java)
            startActivity(intentToHomeFragment)
            finish()
        }

        binding.btnLoginFirst.setOnClickListener {
            Intent(this, LoginActivity::class.java).let {
                startActivity(it)
            }
        }

        binding.btnSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).let {
                startActivity(it)
            }
        }
    }
}