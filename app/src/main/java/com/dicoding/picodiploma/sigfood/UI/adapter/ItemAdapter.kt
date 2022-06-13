package com.dicoding.picodiploma.sigfood.UI.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sigfood.UI.detail.DetailActivity
import com.dicoding.picodiploma.sigfood.databinding.ItemRowBinding
import com.dicoding.picodiploma.sigfood.helper.RecipeDiffCallback
import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.model.SearchRecipe

class ItemAdapter(val listRecipes: ArrayList<Recipes>) : RecyclerView.Adapter<ItemAdapter.CategoryItem>() {


    private var onItemClickCallback : OnItemClickCallback? = null

    inner class CategoryItem(var binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(recipe: Recipes) {
//            with(binding) {
////                Glide.with(imgThumb)
////                    .load(recipe.imageThumb)
////                    .into(imgThumb)
////
////                txtTitle.text = recipe.name
////                txtDifficult.text = recipe.dificulty
////                txtTimes.text = recipe.times
//
//                root.setOnClickListener {
//                    onItemClickCallback?.onItemClicked(recipe)
//                }
//
//            }
//
//        }
    }

    companion object unit {
        override fun toString()= "kotlin.unit"
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItem {
        val view = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItem(view)
    }

    override fun onBindViewHolder(holder: CategoryItem, position: Int) {
//        holder.bind(listRecipes[position])
        holder.itemView.setOnClickListener { listRecipes[position]}

        holder.binding.txtTitle.text = listRecipes[position].name
        Glide.with(holder.itemView.context).load(listRecipes[position].imageThumb).into(holder.binding.imgThumb)
        holder.binding.txtDifficult.text = listRecipes[position].dificulty
        holder.binding.txtTimes.text = listRecipes[position].times

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TITLE, listRecipes[position].key)
            holder.itemView.context.startActivity(intent)


//            fun onItemClicked(data: DetailRecipe) {
//                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
//                intent.putExtra(DetailActivity.EXTRA_THUMB, data.thumbnail)
//                intent.putExtra(DetailActivity.EXTRA_USER, data.author.author)
//                intent.putExtra(DetailActivity.EXTRA_TIMES, data.times)
//                intent.putExtra(DetailActivity.EXTRA_DIFFICULTY, data.dificulty)
//                intent.putExtra(DetailActivity.EXTRA_INGREDIENT, data.ingredients.size)
//                intent.putExtra(DetailActivity.EXTRA_STEP, data.step.size)
//            }

        }
    }

    override fun getItemCount(): Int = listRecipes.size

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:DetailRecipe)
    }
}
