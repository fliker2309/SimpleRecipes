package com.example.simplerecipes.data.database.entity

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
    val unit: String
)
