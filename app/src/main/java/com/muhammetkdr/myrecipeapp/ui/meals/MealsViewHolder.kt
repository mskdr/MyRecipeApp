package com.muhammetkdr.myrecipeapp.ui.meals

import com.muhammetkdr.myrecipeapp.base.BaseViewHolder
import com.muhammetkdr.myrecipeapp.databinding.ItemRowMealsBinding
import com.muhammetkdr.myrecipeapp.model.meal.Meal
import javax.inject.Inject

class MealsViewHolder @Inject constructor (
    private val binding: ItemRowMealsBinding,
    private val onItemClickListener: ((Meal) -> Unit)?
) : BaseViewHolder<Meal>(binding.root) {
    override fun onBind(data: Meal) {
        binding.apply {
            meals = data
            itemView.setOnClickListener { onItemClickListener?.invoke(data) }
        }
    }
}