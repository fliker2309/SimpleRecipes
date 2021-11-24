package com.example.simplerecipes.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplerecipes.data.database.enties.DatabaseIngredient
import com.example.simplerecipes.data.database.enties.DatabaseInstruction
import com.example.simplerecipes.data.database.enties.DatabaseRecipe

private const val DATABASE_NAME = "recipe_db"

@Database(
    version = 1,
    exportSchema = false,
    entities = [DatabaseRecipe::class, DatabaseIngredient::class, DatabaseInstruction::class]
)

abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
