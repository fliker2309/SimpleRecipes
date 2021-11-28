package com.example.simplerecipes.presentation.dispatchers

import android.view.View
import com.example.simplerecipes.domain.entity.Recipe

interface RecipeEventDispatcher {
    fun onRecipePressed(recipe: Recipe, view: View)
}