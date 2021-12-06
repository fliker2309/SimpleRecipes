package com.example.simplerecipes.presentation.ui.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.search.adapters.RecipeEventDispatcher

class FavoritesAdapter(
    private val eventDispatcher: RecipeEventDispatcher
) :
    ListAdapter<Recipe, FavoritesViewHolder>(FavoritesViewHolder.FavoriteRecipeComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.create(parent, eventDispatcher)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }
}
