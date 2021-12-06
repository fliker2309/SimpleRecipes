package com.example.simplerecipes.data.repository

import android.util.Log
import com.example.simplerecipes.data.database.dao.RecipeDao
import com.example.simplerecipes.data.database.enties.toDomainModel
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.network.model.RecipeSearchResponse
import com.example.simplerecipes.data.network.model.toDomainModel
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.entity.toDatabaseModel
import com.example.simplerecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val TAG = "tag"


class RecipeRepositoryImpl @Inject constructor(
    private val service: RecipeService,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getRecipesList(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe> {
        return try {
            val result = service.searchRecipes(
                query = query,
                addRecipeInformation = addRecipeInformation,
                number = number,
                offset = offset
            )
            val recipes = result.results.map { it.toDomainModel() }
            recipes
        } catch (e: IOException) {
            Log.d(TAG, "Recipes not received")
            emptyList()
        }
    }

    override suspend fun getRecipesByIngredient(
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): List<Recipe> {
        return try {
            val result = service.searchRecipesByIngredient(
                addRecipeInformation = addRecipeInformation,
                number = number,
                offset = offset,
                options = options
            )
            val recipes = result.results.map { it.toDomainModel() }
            recipes
        } catch (e: IOException) {
            Log.d(TAG, "Recipes not received")
            emptyList()
        }
    }

    override suspend fun getRecipesResponse(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): RecipeSearchResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeDetails(recipeId: Int): Recipe {
        return try {
            val response = service.requestRecipeInformation(recipeId)
            val recipe = response.toDomainModel()
            recipe
        } catch (e: IOException) {
            Log.d(TAG, "Recipe detail not received,check Internet connection")
            throw Exception("Recipe detail not received")
        }
    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipesWithInformation().map { dbRecipe ->
            dbRecipe.map { it.toDomainModel() }
        }
    }

    override fun getFavoriteRecipeById(id: Int): Flow<Recipe?> {
        return recipeDao.getRecipeByID(id).map { it?.toDomainModel() }
    }

    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        val model = recipe.toDatabaseModel()
        recipeDao.insertRecipe(
            recipe = model.recipe,
            ingredients = model.ingredients,
            instructions = model.instructions
        )
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        val model = recipe.toDatabaseModel()
        recipeDao.deleteRecipe(
            recipe = model.recipe,
            ingredients = model.ingredients,
            instructions = model.instructions
        )
    }

   /* override suspend fun getCategories() {
        TODO("Not yet implemented")
    }*/
}
