package com.example.simplerecipes.domain.repository

import com.example.simplerecipes.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe>

    suspend fun requestRecipeDetails(id: Int): Recipe

    fun requestFavoriteRecipes(): Flow<List<Recipe>>

    suspend fun saveFavouriteRecipe(recipe: Recipe)

    suspend fun deleteFavouriteRecipe(recipe: Recipe)
}
