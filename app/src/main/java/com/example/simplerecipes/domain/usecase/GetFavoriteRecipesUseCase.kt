package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoriteRecipesUseCase {
    fun execute(): Flow<List<Recipe>>
}

class GetFavoriteRecipesUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetFavoriteRecipesUseCase {
    override fun execute(): Flow<List<Recipe>> {
        return repository.getFavoriteRecipes()
    }
}
