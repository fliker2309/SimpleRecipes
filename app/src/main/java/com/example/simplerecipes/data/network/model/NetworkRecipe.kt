package com.example.simplerecipes.data.network.model

import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Instruction
import com.example.simplerecipes.domain.entity.Recipe
import com.google.gson.annotations.SerializedName

data class NetworkRecipe(
    val id: Int,
    val title: String,
    val sourceName: String?,
    val sourceUrl: String?,
    @SerializedName("image")
    val imageUrl: String?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    @SerializedName("analyzedInstructions")
    val instructions: List<NetworkInstructions>?,
    @SerializedName("extendedIngredients")
    val ingredients: List<NetworkIngredient>?
)

data class NetworkInstructions(
    val steps: List<NetworkStep>
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

data class NetworkIngredient(
    val id: Int,
    val name: String,
    val original: String,
    val unit: String
)

fun NetworkRecipe.toDomainModel(): Recipe {
    var instructions = listOf<Instruction>()
    var ingredients = listOf<Ingredient>()

    if (this.instructions?.isNotEmpty() == true) {
        ingredients = this.ingredients?.map {
            it.toDomainModel()
        } ?: emptyList()
    }

    return Recipe(
        id = id,
        title = title,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl ?: "https://spoonacular.com/recipeImages/654959-312x231.jpg",
        readyInMinutes = readyInMinutes,
        servings = servings,
        summary = summary,
        instructions = instructions,
        ingredients = ingredients
    )
}

fun NetworkStep.toDomainModel() = Instruction(number = number, step = step)

fun NetworkIngredient.toDomainModel() =
    Ingredient(id = id, name = name, original = original, unit = unit)
