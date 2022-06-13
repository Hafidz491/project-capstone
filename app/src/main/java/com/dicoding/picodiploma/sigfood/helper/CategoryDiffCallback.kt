package com.dicoding.picodiploma.sigfood.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.picodiploma.sigfood.model.CategoryRecipe

class CategoryDiffCallback(private val mOldCategory: List<CategoryRecipe>, private val mNewCategory: List<CategoryRecipe>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldCategory.size
    }

    override fun getNewListSize(): Int {
        return mNewCategory.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = mOldCategory[oldItemPosition] == mNewCategory[newItemPosition]

}