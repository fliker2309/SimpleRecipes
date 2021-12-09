package com.example.simplerecipes.data.mappers

import com.example.simplerecipes.data.database.dto.DatabaseIngredient
import com.example.simplerecipes.data.database.dto.DatabaseInstruction
import com.example.simplerecipes.data.database.dto.DatabaseRecipeInformation
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Instruction
import com.example.simplerecipes.domain.entity.Recipe

fun DatabaseRecipeInformation.toDomainModel(): Recipe {
    return Recipe(
        id = recipe.id,
        title = recipe.title,
        sourceName = recipe.sourceName,
        imageUrl = recipe.imageUrl,
        sourceUrl = recipe.sourceUrl,
        readyInMinutes = recipe.readyInMinutes,
        summary = recipe.summary,
        ingredients = ingredients.map { it.toDomainModel() },
        instructions = instructions.map { it.toDomainModel() }
    )
}

fun DatabaseIngredient.toDomainModel() =
    Ingredient(id = id, name = name, original = original, amount = amount, unit = unit)

fun DatabaseInstruction.toDomainModel() = Instruction(number = number, step = step)
