package com.example.simplerecipes.data.network.model

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
