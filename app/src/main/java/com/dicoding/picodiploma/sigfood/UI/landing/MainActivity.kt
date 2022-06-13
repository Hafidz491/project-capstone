package com.dicoding.picodiploma.sigfood.UI.landing

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.UI.container.ContainerActivity
import com.dicoding.picodiploma.sigfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnMulai.setOnClickListener {
            val intent = Intent(this, ContainerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {
        val sigfood = ObjectAnimator.ofFloat(binding.sigfood, View.ALPHA, 1f).setDuration(300)
        val coba = ObjectAnimator.ofFloat(binding.coba, View.ALPHA, 1f).setDuration(500)
        val btnMulai = ObjectAnimator.ofFloat(binding.btnMulai, View.ALPHA, 1f).setDuration(2000)

        AnimatorSet().apply {
            playSequentially(sigfood, coba, btnMulai)
        }.start()
    }
}