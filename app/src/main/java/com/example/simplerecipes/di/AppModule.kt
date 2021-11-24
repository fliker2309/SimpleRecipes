package com.example.simplerecipes.di

import android.content.Context
import com.example.simplerecipes.data.database.RecipeDatabase
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.network.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService = RetrofitConfig.recipeService

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = RecipeDatabase.createDatabase(context)

    @Singleton
    @Provides
    fun provideRecipeDao(database: RecipeDatabase) = database.recipeDao()
}
