package com.example.simplerecipes.presentation.ui.favorites

import androidx.lifecycle.*
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.usecase.DeleteFavoriteRecipeUseCase
import com.example.simplerecipes.domain.usecase.GetFavoritesRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesRecipesUseCase: GetFavoritesRecipesUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase
) : ViewModel() {

    private var _favRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val favRecipes: LiveData<List<Recipe>>
        get() = _favRecipes

    init {
        val recipes = getFavoritesRecipesUseCase.getFavoriteRecipes().asLiveData()
    }

    fun deleteFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteFavoriteRecipeUseCase.deleteFavoriteRecipe(recipe)
        }
    }
}
