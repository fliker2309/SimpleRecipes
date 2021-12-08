package com.example.simplerecipes.data.database.dto

import androidx.room.Embedded
import androidx.room.Relation

data class DatabaseRecipeInformation(
    @Embedded val recipe: DatabaseRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val instructions: List<DatabaseInstruction>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<DatabaseIngredient>
)
