package com.example.simplerecipes.data.network

import com.example.simplerecipes.data.network.model.NetworkRecipe
import com.example.simplerecipes.data.network.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

private const val COMPLEX_SEARCH_PATH = "/recipes/complexSearch"
private const val RECIPE_INFO_PATH = "/recipes/{id}/information?includeNutrition=false"

interface RecipeService {
    @GET(COMPLEX_SEARCH_PATH)
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int,
        @Query("offset") offset: Int
    ): RecipeSearchResponse

    @GET(COMPLEX_SEARCH_PATH)
    suspend fun searchRecipesByIngredient(
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int,
        @Query("offset") offset: Int,
        @QueryMap options: Map<String, String>
    ): RecipeSearchResponse

    @GET(RECIPE_INFO_PATH)
    suspend fun requestRecipeInformation(
        @Path("id") id: Int
    ): NetworkRecipe
}
