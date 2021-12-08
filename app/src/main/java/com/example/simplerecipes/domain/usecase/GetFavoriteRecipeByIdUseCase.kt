package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.dto.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoriteRecipeByIdUseCase {
    fun getFavoriteRecipeById(id: Int): Flow<Recipe?>
}

class GetFavoriteRecipeByIdUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : GetFavoriteRecipeByIdUseCase {
    override fun getFavoriteRecipeById(id: Int): Flow<Recipe?> =
        repository.getFavoriteRecipeById(id)
}
