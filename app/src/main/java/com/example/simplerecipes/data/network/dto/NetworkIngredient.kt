package com.example.simplerecipes.data.network.dto

data class NetworkIngredient(
    val id: Int,
    val name: String,
    val original: String,
    val amount: Float,
    val unit: String
)
