package com.example.simplerecipes.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.IngredientItemBinding
import com.example.simplerecipes.domain.entity.Ingredient

class RecipeIngredientsAdapter :
    RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientsViewHolder>() {

    var ingredients: List<Ingredient> = emptyList()
        set(newValue) {
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientItemBinding.inflate(inflater, parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        with(holder.binding) {
            tvIngredient.text = ingredient.name
        }
    }

    override fun getItemCount(): Int = ingredients.size

    class IngredientsViewHolder(val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
