package com.example.simplerecipes.presentation.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ViewRecipeBinding
import com.example.simplerecipes.domain.entity.Recipe

class RecipePagingAdapter(
    private val actionListener: (Recipe) -> Unit
) : PagingDataAdapter<Recipe, RecipeViewHolder>(RecipeViewHolder.RecipeComparator) {
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val viewHolder =
            RecipeViewHolder(ViewRecipeBinding.inflate(LayoutInflater.from(parent.context)))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            getItem(position)?.let { position -> actionListener(position) }
        }
        return viewHolder
    }

 /*   class RecipeViewHolder(private val binding: ViewRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): RecipeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.view_recipe, parent, false)
                val binding = ViewRecipeBinding.bind(view)
                return RecipeViewHolder(binding)
            }
        }

        fun bind(recipe: Recipe) {
            val defaultAuthor = itemView.context.getString(R.string.unknown)
            with(binding) {
                tvName.text = recipe.title
                textAuthor.text = itemView.context.getString(
                    R.string.by_source,
                    recipe.sourceName ?: defaultAuthor
                )
                textTime.text = itemView.context.getString(
                    R.string.minutes_label,
                    recipe.readyInMinutes ?: 0
                )
                ivRecipe.load(recipe.imageUrl) {
                    placeholder(R.drawable.ic_food_placeholder)
                    error(R.drawable.ic_error)
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
    }*/
}
