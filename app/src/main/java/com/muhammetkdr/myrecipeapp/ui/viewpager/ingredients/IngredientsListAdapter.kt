package com.muhammetkdr.myrecipeapp.ui.viewpager.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.muhammetkdr.myrecipeapp.databinding.ItemRowIngredientsBinding
import dagger.Binds
import dagger.Provides
import javax.inject.Inject


class IngredientsListAdapter @Inject constructor() : RecyclerView.Adapter<IngredientsListAdapter.IngredientsViewHolder>() {

    inner class IngredientsViewHolder(val binding: ItemRowIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: String, measure: String) = with(binding) {
            tvNameIngr.text = ingredient
            tvMeasureIngr.text = measure
            itemIngredient = ingredient
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val ItemBinding =
            ItemRowIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(ItemBinding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        val measure = measures[position]
        holder.bind(ingredient, measure)

        holder.itemView.setOnClickListener{
            onItemClickListener?.let {
                it(ingredient)
            }
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    private val diffUtilForIngredients = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //true
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //false
        }
    }

    var DifferForIngredients = AsyncListDiffer(this, diffUtilForIngredients)

    private val diffUtilForMeasures = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //true
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem //false
        }
    }

    var DifferForMeasures = AsyncListDiffer(this, diffUtilForMeasures)

    private var ingredients: List<String>
        get() = DifferForIngredients.currentList
        set(value) = DifferForIngredients.submitList(value)

    private var measures: List<String>
        get() = DifferForMeasures.currentList
        set(value) = DifferForMeasures.submitList(value)



    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

}