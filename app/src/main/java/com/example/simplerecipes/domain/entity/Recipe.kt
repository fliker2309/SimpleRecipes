package com.example.simplerecipes.domain.entity

import java.util.Collections.emptyList

data class Recipe(
    val id: Int,
    val title: String,
    val sourceName: String,
    val sourceUrl: String,
    val imageUrl: String,
    val readyInMinutes: Int,
    val summary: String,
    val instructions: List<Instruction> = emptyList(),
    val ingredients: List<Ingredient> = emptyList()
)
