package com.dicoding.picodiploma.sigfood.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.model.SearchRecipe

class RecipeDiffCallback(private val mOldRecipeList: ArrayList<SearchRecipe>, private val mNewRecipeList: List<SearchRecipe>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldRecipeList.size
    }

    override fun getNewListSize(): Int {
        return mNewRecipeList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldRecipeList[oldItemPosition].name == mNewRecipeList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldRecipeList[newItemPosition]
        val newEmployee = mNewRecipeList[newItemPosition]

        return oldEmployee.name == newEmployee.name && oldEmployee.key == newEmployee.key
    }
}