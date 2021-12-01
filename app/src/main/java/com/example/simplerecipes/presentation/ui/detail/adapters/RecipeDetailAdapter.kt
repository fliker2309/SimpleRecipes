/*
package com.example.simplerecipes.presentation.ui.detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.R
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.domain.entity.RecipeDetailItem

class RecipeDetailAdapter :
    ListAdapter<RecipeDetailItem, RecyclerView.ViewHolder>(RecipeDetailItemDiffCallback()) {

    companion object {
        private const val ITEM_VIEW_TYPE_SUMMARY = 0
        private const val ITEM_VIEW_TYPE_TITLE = 1
        private const val ITEM_VIEW_TYPE_INGREDIENT = 2
        private const val ITEM_VIEW_TYPE_DIRECTION = 3
    }

    var ingredients: List<Ingredient> = emptyList()
        set(newValue) {
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_SUMMARY -> SectionTitleViewHolder.from(parent)
            ITEM_VIEW_TYPE_TITLE -> SectionTitleViewHolder.from(parent)
            ITEM_VIEW_TYPE_INGREDIENT -> IngredientViewHolder.from(parent)
            ITEM_VIEW_TYPE_DIRECTION -> InstructionsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SectionTitleViewHolder -> {
                val item = getItem(position) as RecipeDetailItem.SectionTitle
                holder.bind(item.text)
            }
            is IngredientViewHolder -> {
                val item = getItem(position) as RecipeDetailItem.RecipeIngredient
                holder.bind(item.ingredient.original)
            }
            is InstructionsViewHolder -> {
                val item = getItem(position) as RecipeDetailItem.RecipeDirection
                holder.bind(item.instruction.step, item.instruction.number)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RecipeDetailItem.Summary -> ITEM_VIEW_TYPE_SUMMARY
            is RecipeDetailItem.SectionTitle -> ITEM_VIEW_TYPE_TITLE
            is RecipeDetailItem.RecipeIngredient -> ITEM_VIEW_TYPE_INGREDIENT
            is RecipeDetailItem.RecipeDirection -> ITEM_VIEW_TYPE_DIRECTION
        }
    }

    fun submitRecipeInfo(context: Context, recipe: Recipe) {
        val items = mutableListOf<RecipeDetailItem>()

        if (recipe.ingredients?.isNotEmpty() == true) {
            val ingredientsTitle = context.getString(R.string.ingredients)
            val ingredients = recipe.ingredients.map { ingredient ->
                RecipeDetailItem.RecipeIngredient(ingredient)
            }
            items.add(RecipeDetailItem.SectionTitle(ingredientsTitle, -100))
            items.addAll(ingredients)
        }

        if (recipe.instructions?.isNotEmpty() == true) {
            val stepsTitle = context.getString(R.string.preparation_title)
            val steps = recipe.instructions.map { instruction ->
                RecipeDetailItem.RecipeDirection(instruction)
            }
            items.add(RecipeDetailItem.SectionTitle(stepsTitle, -101))
            items.addAll(steps)
        }

        submitList(items)
    }

    private class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.tvTitle)

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.ingredient_item, parent, false)
                return IngredientViewHolder(view)
            }
        }

        fun bind(text: String) {
            textView.text = text
        }
    }

    private class InstructionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.tvTitle)
        private val numberTextView: TextView = view.findViewById(R.id.tvNumber)

        companion object {
            fun from(parent: ViewGroup): InstructionsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.step_item, parent, false)
                return InstructionsViewHolder(view)
            }
        }

        fun bind(title: String, number: Int) {
            titleTextView.text = title
            numberTextView.text = number.toString()
        }
    }

    private class RecipeDetailItemDiffCallback : DiffUtil.ItemCallback<RecipeDetailItem>() {
        override fun areItemsTheSame(
            oldItem: RecipeDetailItem,
            newItem: RecipeDetailItem
        ) = oldItem.id == newItem.id && oldItem::class == newItem::class

        override fun areContentsTheSame(
            oldItem: RecipeDetailItem,
            newItem: RecipeDetailItem
        ) = oldItem == newItem
    }
}
*/
