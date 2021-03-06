package com.example.simplerecipes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplerecipes.data.database.dao.RecipeDao
import com.example.simplerecipes.data.database.dto.DatabaseIngredient
import com.example.simplerecipes.data.database.dto.DatabaseInstruction
import com.example.simplerecipes.data.database.dto.DatabaseRecipe
import com.example.simplerecipes.utils.Constants.DATABASE_NAME

@Database(
    version = 2,
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
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
