package com.example.simplerecipes.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.example.simplerecipes.domain.dto.Recipe
import com.example.simplerecipes.domain.usecase.DeleteFavoriteRecipeUseCase
import com.example.simplerecipes.domain.usecase.GetFavoriteRecipeByIdUseCase
import com.example.simplerecipes.domain.usecase.GetRecipeDetailsUseCase
import com.example.simplerecipes.domain.usecase.SaveFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val saveFavoriteRecipeUseCase: SaveFavoriteRecipeUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val getFavoriteRecipeByIdUseCase: GetFavoriteRecipeByIdUseCase
) : ViewModel() {

    private var isFavorite = false

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    @ExperimentalCoroutinesApi
    private fun getRecipeDetailsFromDb(id: Int) {
        getFavoriteRecipeByIdUseCase.getFavoriteRecipeById(id).mapLatest { recipe ->
            _recipe.value = recipe
            Log.d("TAG", "сработало чтение из бд")
        }

        /* val details = getFavoriteRecipeByIdUseCase.getFavoriteRecipeById(id).map { recipe ->
             _recipe.value = recipe
         }*/
    }

    fun getRecipeDetailsFromNetwork(id: Int) {
        viewModelScope.launch {
            val details = getRecipeDetailsUseCase.getRecipeDetails(id)
            _recipe.value = details
        }
    }

    @ExperimentalCoroutinesApi
    fun presentRecipeDetails(id: Int) {
        if (isFavorite) {
            getRecipeDetailsFromDb(id)
        } else {
            getRecipeDetailsFromNetwork(id)
        }
    }

    fun saveOrDeleteRecipe() {
        if (isFavorite) {
            deleteFavoriteRecipe()
        } else {
            saveFavoriteRecipe()
        }
    }

    fun isFavorite(id: Int): LiveData<Boolean> {
        return getFavoriteRecipeByIdUseCase.getFavoriteRecipeById(id).map {
            isFavorite = it != null
            isFavorite
        }.asLiveData()
    }

    private fun saveFavoriteRecipe() {
        viewModelScope.launch {
            _recipe.value?.let {
                saveFavoriteRecipeUseCase.saveFavoriteRecipe(it)
            }
        }
    }

    private fun deleteFavoriteRecipe() {
        viewModelScope.launch {
            _recipe.value?.let {
                deleteFavoriteRecipeUseCase.deleteFavoriteRecipe(it)
            }
        }
    }
}
