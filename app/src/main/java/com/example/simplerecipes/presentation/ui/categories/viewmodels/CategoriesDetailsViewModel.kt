/*
package com.example.simplerecipes.presentation.ui.categories.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesDetailsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _recipesFlow = MutableLiveData<PagingData<Recipe>>()
    val recipesFlow: LiveData<PagingData<Recipe>>
        get() = _recipesFlow


    fun requestRecipesForCategory(){
        viewModelScope.launch {
          */
/*  val options = getCategoriesUseCase.requestRecipesForCategory()*//*

        }
    }
}
*/
