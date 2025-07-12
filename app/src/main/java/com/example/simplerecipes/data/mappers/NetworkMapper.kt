package com.example.simplerecipes.data.mappers

import com.example.simplerecipes.data.network.dto.NetworkIngredient
import com.example.simplerecipes.data.network.dto.NetworkRecipe
import com.example.simplerecipes.data.network.dto.NetworkStep
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Instruction
import com.example.simplerecipes.domain.entity.Recipe
import java.util.Collections.emptyList

private const val DEFAULT_IMAGE_URL = "https://spoonacular.com/recipeImages/654959-312x231.jpg"

fun NetworkRecipe.toDomainModel(): Recipe {
    val domainInstructions: List<Instruction> = instructions
        ?.flatMap { it.steps.orEmpty() }
        ?.map { it.toDomainModel() }
        ?: emptyList()

    val domainIngredients: List<Ingredient> = ingredients
        ?.map { it.toDomainModel() }
        ?: emptyList()

    return Recipe(
        id = id,
        title = title,
        sourceName = sourceName ?: "",
        sourceUrl = sourceUrl ?: "",
        imageUrl = imageUrl ?: DEFAULT_IMAGE_URL,
        readyInMinutes = readyInMinutes,
        summary = summary ?: "",
        instructions = domainInstructions,
        ingredients = domainIngredients
    )
}

fun NetworkStep.toDomainModel(): Instruction =
    Instruction(number = number, step = step)

fun NetworkIngredient.toDomainModel(): Ingredient =
    Ingredient(
        id = id,
        name = name ?: "",
        original = original ?: "",
        amount = amount,
        unit = unit ?: ""
    )
