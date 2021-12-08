package com.example.simplerecipes.domain.dto

import com.example.simplerecipes.data.database.dto.DatabaseIngredient
import com.example.simplerecipes.data.database.dto.DatabaseInstruction
import com.example.simplerecipes.data.database.dto.DatabaseRecipe
import com.example.simplerecipes.data.database.dto.DatabaseRecipeInformation

fun Recipe.toDatabaseModel(): DatabaseRecipeInformation {
    val dbRecipe = DatabaseRecipe(
        id = id,
        title = title,
        createdAt = System.currentTimeMillis(),
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl,
        readyInMinutes = readyInMinutes,
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
        instructions = dbInstructions ?: listOf(),
    )
}