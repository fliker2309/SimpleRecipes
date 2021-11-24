package com.example.simplerecipes.data.database.enties

import androidx.room.*

@Entity(tableName = "recipes")
data class DatabaseRecipe(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "source_name")
    val sourceName: String?,
    @ColumnInfo(name = "source_url")
    val sourceUrl: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?
)

data class DatabaseRecipeInformation(
    @Embedded val recipe: DatabaseRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val instructions: List<DatabaseInstruction>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<DatabaseIngredient>
)
