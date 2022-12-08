package com.muhammetkdr.myrecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.myrecipeapp.base.BaseListAdapter
import com.muhammetkdr.myrecipeapp.databinding.ItemRowMealsBinding
import com.muhammetkdr.myrecipeapp.model.meal.Meal

class FavoritesAdapter (private val onMealsItemClickListener: ((Meal) -> Unit)?) :
    BaseListAdapter<Meal>(
        itemsSame = { old, new -> old.idMeal == new.idMeal },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            ItemRowMealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding, onMealsItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoritesViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}