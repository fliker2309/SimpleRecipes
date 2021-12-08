package com.example.simplerecipes.domain.repository

import com.example.simplerecipes.domain.dto.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipesList(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe>

    suspend fun getRecipesByIngredient(
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): List<Recipe>

    suspend fun getRecipeDetails(recipeId: Int): Recipe

    fun getFavoriteRecipes(): Flow<List<Recipe>>

    fun getFavoriteRecipeById(id: Int): Flow<Recipe?>

    suspend fun saveFavoriteRecipe(recipe: Recipe)

    suspend fun deleteFavoriteRecipe(recipe: Recipe)
}
