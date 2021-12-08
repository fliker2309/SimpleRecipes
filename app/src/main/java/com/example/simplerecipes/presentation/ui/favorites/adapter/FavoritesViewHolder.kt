package com.example.simplerecipes.presentation.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ViewRecipeBinding
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.search.adapters.RecipeEventDispatcher

class FavoritesViewHolder(
    private val binding: ViewRecipeBinding,
    private val eventDispatcher: RecipeEventDispatcher
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, eventDispatcher: RecipeEventDispatcher): FavoritesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.view_recipe, parent, false)
            val binding = ViewRecipeBinding.bind(view)
            return FavoritesViewHolder(binding, eventDispatcher)
        }
    }

    fun bind(recipe: Recipe) {
        val defaultAuthor = itemView.context.getString(R.string.unknown)
        with(binding) {
            tvName.text = recipe.title
            btnLike.isVisible = true
            textAuthor.text = itemView.context.getString(
                R.string.by_source,
                recipe.sourceName ?: defaultAuthor
            )
            btnLike.setImageResource(R.drawable.ic_favorites)
            ivRecipe.load(recipe.imageUrl) {
                placeholder(R.drawable.ic_food_placeholder)
                error(R.drawable.ic_error)
            }
            cardRecipe.setOnClickListener {
                eventDispatcher.onRecipePressed(recipe, recipeContainer)
            }
        }
    }

    object FavoriteRecipeComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
