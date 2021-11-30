package com.example.simplerecipes.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(
    @SerialName("results")
    val results: List<NetworkRecipe>,
    @SerialName("offset")
    val offset: Int,
    @SerialName("number")
    val number: Int,
    @SerialName("totalResults")
    val totalResults: Int
)
