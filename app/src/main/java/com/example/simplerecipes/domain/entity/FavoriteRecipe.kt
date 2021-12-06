package com.example.simplerecipes.domain.entity

data class FavoriteRecipe(
    val data: Recipe,
    val isSelected: Boolean
) {
    val id: Int get() = data.id
}
