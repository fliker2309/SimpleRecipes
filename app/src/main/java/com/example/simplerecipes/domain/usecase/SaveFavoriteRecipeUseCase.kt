package com.example.simplerecipes.domain.usecase

import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

interface SaveFavoriteRecipeUseCase {
    suspend fun execute(recipe: Recipe)
}

class SaveFavoriteRecipeUseCaseImpl @Inject constructor(
    private val repository: RecipeRepository
) : SaveFavoriteRecipeUseCase {
    override suspend fun execute(recipe: Recipe) {
        val existing = repository.getFavoriteRecipeById(recipe.id).firstOrNull()
        if (existing == null) {
            repository.saveFavoriteRecipe(recipe)
        }
    }
}
 //Если решишь доработать позже:
//Добавить обратную связь: например, вернуть Boolean, чтобы UI мог показать “Уже добавлено” или “Добавлено успешно”.
//
//Придумать реакцию на случай null с ошибкой загрузки из Flow — например, обернуть в Result.