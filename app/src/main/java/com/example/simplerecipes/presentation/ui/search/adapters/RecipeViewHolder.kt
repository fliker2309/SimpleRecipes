package com.example.simplerecipes.presentation.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ViewRecipeBinding
import com.example.simplerecipes.domain.dto.Recipe

class RecipeViewHolder(
    private val binding: ViewRecipeBinding,
    private val eventDispatcher: RecipeEventDispatcher
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, eventDispatcher: RecipeEventDispatcher): RecipeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.view_recipe, parent, false)
            val binding = ViewRecipeBinding.bind(view)
            return RecipeViewHolder(binding, eventDispatcher)
        }
    }

    fun bind(recipe: Recipe) {
        val defaultAuthor = itemView.context.getString(R.string.unknown)
        with(binding) {
            tvName.text = recipe.title
            btnLike.isVisible = false
            textAuthor.text = itemView.context.getString(
                R.string.by_source,
                recipe.sourceName ?: defaultAuthor
            )
            ivRecipe.load(recipe.imageUrl) {
                placeholder(R.drawable.ic_food_placeholder)
                error(R.drawable.ic_error)
                crossfade(true)
                crossfade(1000)
            }
            cardRecipe.setOnClickListener {
                eventDispatcher.onRecipePressed(recipe, recipeContainer)
            }
        }
    }

    object RecipeComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
