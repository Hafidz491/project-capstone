package com.dicoding.picodiploma.sigfood.UI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sigfood.databinding.ItemRowBinding
import com.dicoding.picodiploma.sigfood.helper.RecipeDiffCallback
import com.dicoding.picodiploma.sigfood.model.SearchRecipe

class RecipeAdapter(val recipeList : ArrayList<SearchRecipe>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    fun setList(recipe: List<SearchRecipe>){
        val diffCallback = RecipeDiffCallback(this.recipeList, recipe)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList.clear()
        recipeList.addAll(recipe)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        holder.binding.txtTitle.text = recipeList[position].name
        Glide.with(holder.itemView.context).load(recipeList[position].imageThumb).into(holder.binding.imgThumb)
        holder.binding.txtDifficult.text = recipeList[position].difficulty
        holder.binding.txtTimes.text = recipeList[position].times
    }

    override fun getItemCount(): Int = recipeList.size
}