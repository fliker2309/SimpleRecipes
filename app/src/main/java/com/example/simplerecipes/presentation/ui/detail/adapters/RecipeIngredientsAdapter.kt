package com.example.simplerecipes.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.IngredientItemBinding
import com.example.simplerecipes.domain.entity.Ingredient

class RecipeIngredientsAdapter :
    RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientViewHolder>() {

    var ingredients: List<Ingredient> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

    fun submitIngredients(newIngredients: List<Ingredient>) {
        ingredients = newIngredients
        notifyDataSetChanged()
    }

    class IngredientViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }

        fun bind(ingredient: Ingredient) {
            binding.tvIngredient.text = ingredient.name
        }
    }
}
