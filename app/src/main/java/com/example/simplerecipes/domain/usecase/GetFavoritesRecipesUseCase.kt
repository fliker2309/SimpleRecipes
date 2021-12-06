package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoritesRecipesUseCase {
    fun getFavoriteRecipes(): Flow<List<Recipe>>
}

class GetFavoritesRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetFavoritesRecipesUseCase {
    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return repository.getFavoriteRecipes()
    }
}
