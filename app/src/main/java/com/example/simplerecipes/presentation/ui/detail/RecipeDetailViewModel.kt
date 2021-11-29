package com.example.simplerecipes.presentation.ui.detail

import androidx.lifecycle.*
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    private var isFavorite = false

    fun getRecipeDetails(id: Int) {
        viewModelScope.launch {
            val details = repository.getRecipeDetails(id)
            _recipe.value = details
        }
    }

    fun presentRecipeDetails(recipe: Recipe) {
        _recipe.value = recipe
    }

    fun saveOrDeleteRecipe() {
        if (isFavorite) {
            deleteFavoriteRecipe()
        } else {
            saveFavoriteRecipe()
        }
    }

    fun isFavorite(id: Int): LiveData<Boolean> {
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
    }
}
