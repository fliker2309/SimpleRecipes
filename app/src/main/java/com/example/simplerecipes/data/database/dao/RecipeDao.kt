package com.example.simplerecipes.data.database.dao

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.simplerecipes.data.database.entity.DatabaseIngredient
import com.example.simplerecipes.data.database.entity.DatabaseInstruction
import com.example.simplerecipes.data.database.entity.DatabaseRecipe
import com.example.simplerecipes.data.database.entity.DatabaseRecipeInformation
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipes ORDER BY created_at DESC")
    fun getRecipesWithInformation(): Flow<List<DatabaseRecipeInformation>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getRecipeByID(id: Int): Flow<DatabaseRecipeInformation?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(
        recipe: DatabaseRecipe,
        ingredients: List<DatabaseIngredient>,
        instructions: List<DatabaseInstruction>
    )

    @Delete
    suspend fun deleteRecipe(
        recipe: DatabaseRecipe,
        ingredients: List<DatabaseIngredient>,
        instructions: List<DatabaseInstruction>
    )
}
