package com.example.simplerecipes.domain.entity

data class Recipe(
    val id: Int,
    val title: String,
    val sourceName: String?,
    val sourceUrl: String?,
    val imageUrl: String,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val instructions: List<Instruction>?,
    val ingredients: List<Ingredient>?
)

data class Instruction(
    val number: Int,
    val step: String
)

data class Ingredient(
    val id: Int,
    val name: String,
    val original: String,
    val unit: String
)
