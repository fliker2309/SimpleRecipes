package com.example.simplerecipes.data.database

import android.content.Context
import androidx.room.*
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
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration() // Гибкое обновление схемы
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
