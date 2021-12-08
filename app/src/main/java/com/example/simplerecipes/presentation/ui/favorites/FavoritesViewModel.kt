package com.example.simplerecipes.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.usecase.GetFavoriteRecipeByIdUseCase
import com.example.simplerecipes.domain.usecase.GetFavoritesRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesRecipesUseCase: GetFavoritesRecipesUseCase,
    private val getFavoriteRecipeByIdUseCase: GetFavoriteRecipeByIdUseCase

) : ViewModel() {

    val favRecipes: LiveData<List<Recipe>> =
        getFavoritesRecipesUseCase.getFavoriteRecipes().asLiveData()



}
