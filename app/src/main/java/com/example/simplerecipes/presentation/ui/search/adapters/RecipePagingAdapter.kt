package com.example.simplerecipes.presentation.ui.search.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.dispatchers.RecipeEventDispatcher

class RecipePagingAdapter(
    private val eventDispatcher: RecipeEventDispatcher
) : PagingDataAdapter<Recipe, RecipeViewHolder>(RecipeViewHolder.RecipeComparator) {
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent, eventDispatcher)
    }
}
