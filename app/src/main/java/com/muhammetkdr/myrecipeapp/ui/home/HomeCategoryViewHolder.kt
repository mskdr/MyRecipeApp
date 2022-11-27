package com.muhammetkdr.myrecipeapp.ui.home

import com.muhammetkdr.myrecipeapp.base.BaseViewHolder
import com.muhammetkdr.myrecipeapp.databinding.ItemRowCategoryBinding
import com.muhammetkdr.myrecipeapp.model.category.Category

class HomeCategoryViewHolder(
    private val binding: ItemRowCategoryBinding,
    private val onItemClickListener: ((Category) -> Unit)?
) : BaseViewHolder<Category>(binding.root) {
    override fun onBind(data: Category) {
        binding.apply {
            category = data
            itemView.setOnClickListener { onItemClickListener?.invoke(data) }
        }
    }
}