package com.example.simplerecipes.data.repository

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl : RecipeRepository {
    override suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun requestRecipeDetails(id: Int): Recipe {
        TODO("Not yet implemented")
    }

    override fun requestFavoriteRecipes(): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavouriteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }
}