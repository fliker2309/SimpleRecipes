package com.example.simplerecipes.data.network.dto

import com.example.simplerecipes.domain.dto.Ingredient
import com.example.simplerecipes.domain.dto.Instruction
import com.example.simplerecipes.domain.dto.Recipe
import com.google.gson.annotations.SerializedName

data class NetworkRecipe(
    val id: Int,
    val sourceName: String?,
    val title: String,
    val readyInMinutes: Int?,
    val sourceUrl: String?,
    @SerializedName("image")
    val imageUrl: String?,
    val summary: String?,
    @SerializedName("analyzedInstructions")
    val instructions: List<NetworkInstructions>?,
    @SerializedName("ingredients")
    val ingredients: List<NetworkIngredient>
)

data class NetworkInstructions(
    val steps: List<NetworkStep>
)

data class NetworkIngredient(
    val id: Int,
    val name: String,
    val original: String,
    val unit: String
)

data class NetworkStep(
    val number: Int,
    val step: String,
    val ingredients: List<NetworkRecipeElement>,
    val equipment: List<NetworkRecipeElement>
)

data class NetworkRecipeElement(
    val id: Int,
    val name: String,
    val image: String
)

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
