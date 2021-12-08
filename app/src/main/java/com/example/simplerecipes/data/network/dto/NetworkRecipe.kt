package com.example.simplerecipes.data.network.dto

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
    val ingredients: List<NetworkIngredient>?
)
