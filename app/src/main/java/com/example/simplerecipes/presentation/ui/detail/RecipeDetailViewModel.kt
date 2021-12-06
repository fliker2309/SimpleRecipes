package com.example.simplerecipes.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.usecase.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {
    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    private var _loadingRecipeDetails: MutableLiveData<Boolean> = MutableLiveData()
    val loadingRecipeDetails: LiveData<Boolean>
        get() = _loadingRecipeDetails

    private var isFavorite = false

    fun getRecipeDetails(id: Int) {
        viewModelScope.launch {
            _loadingRecipeDetails.value = true
            val details = getRecipeDetailsUseCase.getRecipeDetails(id)
            _recipe.value = details
            _loadingRecipeDetails.value = false
        }
    }

    fun presentRecipeDetails(recipe: Recipe) {
        _recipe.value = recipe
    }

    /* fun saveOrDeleteRecipe() {
         if (isFavorite) {
             deleteFavoriteRecipe()
         } else {
             saveFavoriteRecipe()
         }
     }*/

    /* fun isFavorite(id: Int): LiveData<Boolean> {
         return repository.getFavoriteRecipeById(id).map {
             isFavorite = it != null
             isFavorite
         }.asLiveData()
     }

     private fun saveFavoriteRecipe() {
         viewModelScope.launch {
             _recipe.value?.let {
                 repository.saveFavoriteRecipe(it)
             }
         }
     }

     private fun deleteFavoriteRecipe() {
         viewModelScope.launch {
             _recipe.value?.let {
                 repository.deleteFavoriteRecipe(it)
             }
         }
     }*/
}
