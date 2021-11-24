package com.example.simplerecipes.data.repository

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl : RecipeRepository {
    override suspend fun getRecipesList(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipesByIngredient(
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeDetails(id: Int): Recipe {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomRecipe(): Recipe {
        TODO("Not yet implemented")
    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteRecipeById(id: Int): Flow<Recipe?> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

}