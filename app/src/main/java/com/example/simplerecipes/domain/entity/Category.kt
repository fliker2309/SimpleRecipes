package com.example.simplerecipes.domain.entity

data class Category(
    val name: String,
    val items: List<CategoryItem>
)
