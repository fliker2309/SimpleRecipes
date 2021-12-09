package com.example.simplerecipes.presentation.ui.detail.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.IngredientItemBinding
import com.example.simplerecipes.domain.entity.Ingredient
import com.example.simplerecipes.utils.Constants.TAG

class RecipeIngredientsAdapter :
    RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientViewHolder>() {

    var ingredients: List<Ingredient> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "There are ${ingredients.size} ingredients")
        return ingredients.size
    }

    fun submitIngredients(newIngredients: List<Ingredient>) {
        ingredients = newIngredients
        notifyDataSetChanged()
        Log.d(TAG, "Ingredient Adaper submitData")
    }

    class IngredientViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): IngredientViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }

        fun bind(ingredient: Ingredient) {
            with(binding) {
                tvOriginal.text = ingredient.original
            }
        }
    }
}
