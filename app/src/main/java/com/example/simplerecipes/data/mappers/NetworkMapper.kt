package com.example.simplerecipes.data.mappers

import com.example.simplerecipes.data.network.dto.NetworkIngredient
import com.example.simplerecipes.data.network.dto.NetworkRecipe
import com.example.simplerecipes.data.network.dto.NetworkStep
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Instruction
import com.example.simplerecipes.domain.entity.Recipe

fun NetworkRecipe.toDomainModel(): Recipe {
    var instructions = listOf<Instruction>()
    var ingredients = listOf<Ingredient>()

    if (this.instructions?.isNotEmpty() == true) {
        instructions = this.instructions.first().steps.map {
            it.toDomainModel()
        }
    }
    if (this.ingredients?.isNotEmpty() == true) {
        ingredients = this.ingredients.map {
            it.toDomainModel()
        }
    }

    return Recipe(
        id = id,
        title = title,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl ?: "https://spoonacular.com/recipeImages/654959-312x231.jpg",
        readyInMinutes = readyInMinutes,
        summary = summary,
        instructions = instructions,
        ingredients = ingredients
    )
}

fun NetworkStep.toDomainModel() = Instruction(number = number, step = step)

fun NetworkIngredient.toDomainModel() =
    Ingredient(id = id, name = name, original = original, unit = unit)
