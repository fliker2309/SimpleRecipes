package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import javax.inject.Inject

interface GetRecipeDetailsUseCase {
    suspend fun getRecipeDetails(recipeId: Int): Recipe
}

class GetRecipeDetailsUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetRecipeDetailsUseCase {
    override suspend fun getRecipeDetails(recipeId: Int): Recipe = repository.getRecipeDetails(recipeId)
}
