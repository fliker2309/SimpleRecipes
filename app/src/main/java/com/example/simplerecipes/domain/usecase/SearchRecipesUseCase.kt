package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.dto.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import javax.inject.Inject

interface SearchRecipesUseCase {
    suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe>
}

class SearchRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : SearchRecipesUseCase {
    override suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe> = repository.getRecipesList(query, addRecipeInformation, number, offset)
}
