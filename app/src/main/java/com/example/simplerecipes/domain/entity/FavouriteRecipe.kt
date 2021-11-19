package com.example.simplerecipes.domain.entity

data class FavouriteRecipe(
    val data: Recipe,
    val isSelected: Boolean
) {
    val id: Int get() = data.id
}
