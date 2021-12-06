package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import javax.inject.Inject

interface DeleteFavoriteRecipeUseCase {
    suspend fun deleteFavoriteRecipe(recipe: Recipe)
}

class DeleteFavoriteRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : DeleteFavoriteRecipeUseCase {
    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        repository.deleteFavoriteRecipe(recipe)
    }
}
