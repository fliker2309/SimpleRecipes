package com.example.simplerecipes.data.database.enties

import androidx.room.*
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Instruction
import com.example.simplerecipes.domain.entity.Recipe

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

fun DatabaseRecipeInformation.toDomainModel(): Recipe {
    return Recipe(
        id = recipe.id,
        title = recipe.title,
        sourceName = recipe.sourceName,
        imageUrl = recipe.imageUrl,
        sourceUrl = recipe.sourceUrl,
        readyInMinutes = recipe.readyInMinutes,
        servings = recipe.servings,
        summary = recipe.summary,
        ingredients = ingredients.map { it.toDomainModel() },
        instructions = instructions.map { it.toDomainModel() }
    )
}

fun DatabaseIngredient.toDomainModel() =
    Ingredient(id = id, name = name, original = original, unit = unit)

fun DatabaseInstruction.toDomainModel() = Instruction(number = number, step = step)
