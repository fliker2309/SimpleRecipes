package com.example.simplerecipes.domain.usecase

import android.util.Log
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import com.example.simplerecipes.utils.Constants.TAG
import javax.inject.Inject

interface SaveFavoriteRecipeUseCase {
    suspend fun saveFavoriteRecipe(recipe: Recipe)
}

class SaveFavoriteRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : SaveFavoriteRecipeUseCase {
    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        Log.d(TAG, "saveFavUseCase")
        repository.saveFavoriteRecipe(recipe)
    }
}
