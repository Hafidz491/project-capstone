package com.dicoding.picodiploma.sigfood.UI.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.UI.ViewModelFactory
import com.dicoding.picodiploma.sigfood.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var key: String
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        key = intent.getStringExtra(EXTRA_TITLE).toString()
        val user =intent.getStringExtra(EXTRA_USER).toString()
        val avatarUrl = intent.getStringExtra(EXTRA_THUMB)

        val mBundle = Bundle()
        mBundle.putString(EXTRA_TITLE, key)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this)
        val key = intent.extras?.getString(EXTRA_TITLE)
        viewModel.setRecipeData(key!!)

        detailUserViewModel.isLoading.observe(this) {
            showLoadingDetail(it)
        }

        viewModel.detailResponse.observe(this){
            for (i in it.results?.indices!!){
                val textDiffItem = findViewById<TextView>(R.id.textDiffItem)
                textDiffItem.text = it.results!![i]?.dificulty

                val textDiffTime = findViewById<TextView>(R.id.textTimesItem)
                textDiffTime.text = it.results!![i]?.times
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.results!![i]?.thumb)
                        .into(imgThumb)

                    recipeTitle.text = it.results!![i]?.title
                }
            }
        }


    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailUserViewModel::class.java)
    }

    private fun showLoadingDetail(state: Boolean){
        if (state){
            binding.pgMain.visibility = View.VISIBLE
        }else{
            binding.pgMain.visibility = View.GONE
        }
    }

    companion object {
        var EXTRA_THUMB = "extra_thumb"
        var EXTRA_TITLE = "extra_title"
        var EXTRA_USER = "extra_user"
        var EXTRA_TIMES = "extra_times"
        var EXTRA_DIFFICULTY = "extra_difficulty"
        var EXTRA_INGREDIENT = "extra_ingredient"
        var EXTRA_STEP = "extra_step"
    }
}