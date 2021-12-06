package com.example.simplerecipes.di

import com.example.simplerecipes.data.repository.RecipeRepositoryImpl
import com.example.simplerecipes.domain.repository.RecipeRepository
import com.example.simplerecipes.domain.usecase.* // ktlint-disable no-wildcard-imports
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
    abstract fun saveFavoriteRecipeUseCase(
        saveFavoriteRecipeUseCase: SaveFavoriteRecipeUseCaseImpl
    ): SaveFavoriteRecipeUseCase

    @Binds
    abstract fun deleteFavoriteRecipeUseCase(
        deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCaseImpl
    ): DeleteFavoriteRecipeUseCase

    @Binds
    abstract fun getFavoritesRecipesUseCase(
        getFavoritesRecipesUseCase: GetFavoritesRecipesUseCaseImpl
    ): GetFavoritesRecipesUseCase
}
