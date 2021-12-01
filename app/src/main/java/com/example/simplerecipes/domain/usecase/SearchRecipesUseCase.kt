package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.data.repository.SearchPagingSource
import com.example.simplerecipes.domain.entity.Recipe
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
