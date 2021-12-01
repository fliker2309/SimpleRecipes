package com.example.simplerecipes.data.datasource

import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.network.model.NetworkRecipe
import com.example.simplerecipes.data.network.model.RecipeSearchResponse
import javax.inject.Inject

interface RecipeRemoteDataSource {

    suspend fun getRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<NetworkRecipe>

    suspend fun getRecipeDetails(recipeId: Int): NetworkRecipe
}

class RecipeRemoteDataSourceImpl @Inject constructor(
    private val service: RecipeService
) : RecipeRemoteDataSource {
    override suspend fun getRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<NetworkRecipe> =
        service.searchRecipes(query, addRecipeInformation, number, offset).results

    override suspend fun getRecipeDetails(recipeId: Int): NetworkRecipe =
        service.requestRecipeInformation(recipeId)
}
