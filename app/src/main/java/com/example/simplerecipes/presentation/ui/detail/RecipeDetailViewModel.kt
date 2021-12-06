package com.example.simplerecipes.presentation.ui.detail

import androidx.lifecycle.*
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.usecase.*
import com.example.simplerecipes.presentation.ui.SaveOrDeleteRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val saveFavoriteRecipeUseCase: SaveFavoriteRecipeUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val getFavoritesRecipesUseCase: GetFavoritesRecipesUseCase,
    private val getFavoriteRecipeById: GetFavoriteRecipeByIdUseCase
) : ViewModel()/*, SaveOrDeleteRecipe*/ {

    private var isFavorite = false

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    private var _loadingRecipeDetails: MutableLiveData<Boolean> = MutableLiveData()
    val loadingRecipeDetails: LiveData<Boolean>
        get() = _loadingRecipeDetails

    fun getRecipeDetailsFromDb(id: Int) {
        getFavoriteRecipeById.getFavoriteRecipeById(id)
    }

    fun getRecipeDetailsFromNetwork(id: Int) {
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
/*
    fun saveOrDeleteRecipe() {
        if (isFavorite) {
            deleteFavoriteRecipe()
        } else {
            saveFavoriteRecipe()
        }
    }

    fun isFavorite(id: Int): LiveData<Boolean> {
        return getFavoritesRecipesUseCase.getFavoriteRecipeById(id).map {
            isFavorite = it != null
            isFavorite
        }.asLiveData()*/
    }

/*    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _recipe.value?.let {
                saveFavoriteRecipeUseCase.saveFavoriteRecipe(it)
            }
        }
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _recipe.value?.let {
                deleteFavoriteRecipeUseCase.deleteFavoriteRecipe(it)
            }
        }
    }*/
/*}*/
