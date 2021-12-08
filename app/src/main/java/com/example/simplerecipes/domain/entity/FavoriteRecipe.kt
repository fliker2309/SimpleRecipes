package com.example.simplerecipes.domain.entity

data class FavoriteRecipe(
    val data: List<Recipe>,
    val isFavorite: Boolean
)
