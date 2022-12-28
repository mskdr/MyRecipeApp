package com.muhammetkdr.myrecipeapp.ui.viewpager.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.myrecipeapp.databinding.ItemRowIngredientsBinding
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
        val binding =
            ItemRowIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
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
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    var differForIngredients = AsyncListDiffer(this, diffUtilForIngredients)

    private val diffUtilForMeasures = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    var differForMeasures = AsyncListDiffer(this, diffUtilForMeasures)

    private var ingredients: List<String>
        get() = differForIngredients.currentList
        set(value) = differForIngredients.submitList(value)

    private var measures: List<String>
        get() = differForMeasures.currentList
        set(value) = differForMeasures.submitList(value)

    var onItemClickListener: ((String) -> Unit)? = null

//    fun setOnRvItemClickListener(listener: (String) -> Unit) {
//        onItemClickListener = listener
//    }
}