package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoritesRecipesUseCase {
    fun getFavoriteRecipe(): Flow<List<Recipe>>
}

class GetFavoritesRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetFavoritesRecipesUseCase {
    override fun getFavoriteRecipe(): Flow<List<Recipe>> {
        return repository.getFavoriteRecipes()
    }
}
