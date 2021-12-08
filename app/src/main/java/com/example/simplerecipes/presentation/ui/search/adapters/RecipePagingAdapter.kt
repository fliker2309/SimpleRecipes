package com.example.simplerecipes.presentation.ui.search.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.simplerecipes.domain.dto.Recipe

interface RecipeEventDispatcher {
    fun onRecipePressed(recipe: Recipe, view: View)
}

class RecipePagingAdapter(
    private val eventDispatcher: RecipeEventDispatcher
) : PagingDataAdapter<Recipe, RecipeViewHolder>(RecipeViewHolder.RecipeComparator) {
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent, eventDispatcher)
    }
}
