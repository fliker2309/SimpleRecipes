package com.example.simplerecipes.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class DatabaseIngredient(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    val name: String,
    val original: String,
    val amount: Float,
    val unit: String
)
