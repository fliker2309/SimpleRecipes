package com.example.simplerecipes.data.database.enties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instructions")
data class DatabaseInstruction(
    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,
    val number: Int,
    val step: String
) {
    @PrimaryKey
    var id: String = "$recipeId-$number"
}
