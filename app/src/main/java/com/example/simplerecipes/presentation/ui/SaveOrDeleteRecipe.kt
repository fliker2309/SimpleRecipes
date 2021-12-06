package com.example.simplerecipes.presentation.ui

import com.example.simplerecipes.domain.entity.Recipe

interface SaveOrDeleteRecipe {
    suspend fun saveFavoriteRecipe(recipe: Recipe)
    suspend fun deleteFavoriteRecipe(recipe: Recipe)
}
