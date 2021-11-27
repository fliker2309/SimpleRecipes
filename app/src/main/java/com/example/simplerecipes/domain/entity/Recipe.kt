package com.example.simplerecipes.domain.entity

import com.example.simplerecipes.data.database.enties.DatabaseIngredient
import com.example.simplerecipes.data.database.enties.DatabaseInstruction
import com.example.simplerecipes.data.database.enties.DatabaseRecipe
import com.example.simplerecipes.data.database.enties.DatabaseRecipeInformation

data class Recipe(
    val id: Int,
    val title: String,
    val sourceName: String?,
    val sourceUrl: String?,
    val imageUrl: String,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val instructions: List<Instruction>?,
    val ingredients: List<Ingredient>?
)

data class Instruction(
    val number: Int,
    val step: String
)

data class Ingredient(
    val id: Int,
    val name: String,
    val original: String,
    val unit: String
)

fun Recipe.toDatabaseModel(): DatabaseRecipeInformation {
    val dbRecipe = DatabaseRecipe(
        id = id,
        title = title,
        createdAt = System.currentTimeMillis(),
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl,
        readyInMinutes = readyInMinutes,
        servings = servings,
        summary = summary
    )

    val dbIngredients = ingredients?.map { ingredient ->
        DatabaseIngredient(
            id = ingredient.id,
            recipeId = id,
            name = ingredient.name,
            original = ingredient.original,
            unit = ingredient.unit

        )
    }

    val dbInstructions = instructions?.map { instruction ->
        DatabaseInstruction(
            recipeId = id,
            number = instruction.number,
            step = instruction.step
        )
    }

    return DatabaseRecipeInformation(
        recipe = dbRecipe,
        ingredients = dbIngredients ?: listOf(),
        instructions = dbInstructions ?: listOf()
    )
}
