package com.example.simplerecipes.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.repository.FilteredRecipesPagingSource
import com.example.simplerecipes.domain.entity.CategoryItem
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend fun requestRecipesForCategory(categoryItem: CategoryItem)
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val service: RecipeService
) : GetCategoriesUseCase {

    var recipesFlow: Flow<PagingData<Recipe>>? = null
    override suspend fun requestRecipesForCategory(categoryItem: CategoryItem) {
        recipesFlow = Pager(PagingConfig(Constants.RECIPES_PER_PAGE)) {
            val options = mapOf(categoryItem.type to categoryItem.name)
            FilteredRecipesPagingSource(service, options)
        }.flow
    }
}
