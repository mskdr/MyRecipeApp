package com.muhammetkdr.myrecipeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhammetkdr.myrecipeapp.common.utils.Resource
import com.muhammetkdr.myrecipeapp.domain.usecase.list.GetCategoriesUseCase
import com.muhammetkdr.myrecipeapp.model.category.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCategoriesUseCase : GetCategoriesUseCase) : ViewModel() {
    
    private val _categoryList : MutableLiveData<Resource<CategoryModel>> = MutableLiveData(Resource.Loading)
    val categoryList: LiveData<Resource<CategoryModel>> get() = _categoryList

    init {
        getCategories()
    }

    fun getCategories() = viewModelScope.launch {
            _categoryList.value = Resource.Loading
            _categoryList.value = getCategoriesUseCase()!!
        }
}
