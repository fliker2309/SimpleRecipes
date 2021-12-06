/*
package com.example.simplerecipes.presentation.ui.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ViewCategoryBinding
import com.example.simplerecipes.domain.entity.CategoryItem
import com.example.simplerecipes.presentation.ui.categories.dispatchers.CategoryEventDispatcher

class CategoriesViewHolder(
    private val binding: ViewCategoryBinding,
    private val eventDispatcher: CategoryEventDispatcher
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            eventDispatcher: CategoryEventDispatcher
        ): CategoriesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.view_category, parent, false)
            val binding = ViewCategoryBinding.bind(view)
            return CategoriesViewHolder(binding, eventDispatcher)
        }
    }

    fun bind(categoryItem: CategoryItem) {
        with(binding) {
            tvCategoryName.text = categoryItem.name
            ivCategoryItem.load(categoryItem.imageUrl) {
                placeholder(R.drawable.ic_food_placeholder)
                error(R.drawable.ic_error)
            }
            cardCategory.setOnClickListener {
                eventDispatcher.onCategoryPressed(categoryItem, cardCategory)
            }
        }
    }
}
*/
