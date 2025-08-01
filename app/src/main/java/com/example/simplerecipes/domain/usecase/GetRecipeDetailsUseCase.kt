package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import javax.inject.Inject

interface GetRecipeDetailsUseCase {
    suspend fun execute(recipeId: Int): Result<Recipe>
}

class GetRecipeDetailsUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetRecipeDetailsUseCase {
    override suspend fun execute(recipeId: Int): Result<Recipe> = try {
        val recipe = repository.getRecipeDetails(recipeId)
        Result.success(recipe)
    } catch (e: Exception) {
        Result.failure(e)
    }
}