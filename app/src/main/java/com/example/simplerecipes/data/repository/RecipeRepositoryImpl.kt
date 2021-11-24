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

    override suspend fun getRecipeDetails(id: Int): Recipe {
        TODO("Not yet implemented")
    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }
}