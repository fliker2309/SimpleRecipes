package com.example.simplerecipes.di

import com.example.simplerecipes.data.repository.RecipeRepositoryImpl
import com.example.simplerecipes.domain.repository.RecipeRepository
import com.example.simplerecipes.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    abstract fun getRecipeDetailsUseCase(
        getRecipeDetailsUseCase: GetRecipeDetailsUseCaseImpl
    ): GetRecipeDetailsUseCase

    @Binds
    abstract fun searchRecipesUseCase(
        searchRecipesUseCase: SearchRecipesUseCaseImpl
    ): SearchRecipesUseCase

    @Binds
    abstract fun getCategoriesUseCase(
        getCategoriesUseCase: GetCategoriesUseCaseImpl
    ): GetCategoriesUseCase
}
