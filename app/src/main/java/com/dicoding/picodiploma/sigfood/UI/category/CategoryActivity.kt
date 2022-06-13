package com.dicoding.picodiploma.sigfood.UI.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sigfood.R
import com.dicoding.picodiploma.sigfood.UI.ViewModelFactory
import com.dicoding.picodiploma.sigfood.UI.adapter.CategoryAdapter
import com.dicoding.picodiploma.sigfood.databinding.ActivityCategoryBinding
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.model.ResultItem
import com.dicoding.picodiploma.sigfood.response.RecipesResponse

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var key: String
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = intent.getStringExtra(EXTRA_CATEGORY).toString()

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this)
        viewModel.setCategoryRecipe(category)

//        viewModel.categoryResponse.observe(this) {
//            for (i in it.results?.indices!!){
//                val textDiffItem = findViewById<TextView>(R.id.textDiffItem)
//                textDiffItem.text = it.results!![i]?.dificulty
//
//                val textDiffTime = findViewById<TextView>(R.id.textTimesItem)
//                textDiffTime.text = it.results!![i]?.times
//                binding.apply {
//                    Glide.with(this@CategoryActivity)
//                        .load(it.results!![i]?.thumb)
//                        .into()
//
//                    recipeTitle.text = it.results!![i]?.title
//                }
//            }
//        }
        categoryViewModel.isLoading.observe(this) {
            isLoading(it)
        }

        viewModel.categoryResponse.observe(this) {
            setAdapter(it.results)
        }
    }

    private fun setAdapter(listCategory: List<ResultItem?>?) {
        val item = ArrayList<ResultItem>()

        for (i in listCategory?.indices!!){
            item.add(listCategory[i]!!)
        }

        val adapter = CategoryAdapter(item)
        binding.apply {
            rvRecipe.layoutManager = LinearLayoutManager(this@CategoryActivity)
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading){
            binding.pgMain.visibility = View.VISIBLE
            binding.rvRecipe.visibility = View.GONE
        }else{
            binding.pgMain.visibility = View.GONE
            binding.rvRecipe.visibility = View.VISIBLE
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): CategoryViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(CategoryViewModel::class.java)
    }

    companion object {
        val EXTRA_CATEGORY = "extra_category"
    }
}