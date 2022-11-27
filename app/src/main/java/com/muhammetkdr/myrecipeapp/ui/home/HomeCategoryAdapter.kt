package com.muhammetkdr.myrecipeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.myrecipeapp.base.BaseListAdapter
import com.muhammetkdr.myrecipeapp.databinding.ItemRowCategoryBinding
import com.muhammetkdr.myrecipeapp.model.category.Category

class HomeCategoryAdapter(private val onCategoryItemClickListener: ((Category) -> Unit)?) :
    BaseListAdapter<Category>(
        itemsSame = { old, new -> old.idCategory == new.idCategory },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding =
            ItemRowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeCategoryViewHolder(binding, onCategoryItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeCategoryViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}