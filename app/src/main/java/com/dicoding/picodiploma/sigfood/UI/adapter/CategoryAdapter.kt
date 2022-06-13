package com.dicoding.picodiploma.sigfood.UI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sigfood.databinding.ItemRowBinding
import com.dicoding.picodiploma.sigfood.helper.CategoryDiffCallback
import com.dicoding.picodiploma.sigfood.helper.RecipeDiffCallback
import com.dicoding.picodiploma.sigfood.model.CategoryRecipe
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.model.ResultItem
import java.util.*

class CategoryAdapter(val listCategory: ArrayList<ResultItem>): RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    private val category: MutableList<CategoryRecipe> = mutableListOf()
    internal var itemTouch: ((category: CategoryRecipe) -> Unit)? = null

    inner class CategoryVH(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryVH {
        val view = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryVH, position: Int) {
        holder.itemView.setOnClickListener { listCategory[position] }

        holder.binding.txtTitle.text = listCategory[position].title
        Glide.with(holder.itemView.context).load(listCategory[position].thumb).into(holder.binding.imgThumb)
        holder.binding.txtDifficult.text = listCategory[position].dificulty
        holder.binding.txtTimes.text = listCategory[position].times
    }

    override fun getItemCount(): Int {
        return category.size
    }


}