package com.example.simplerecipes.presentation.ui.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.ViewCategoryBinding
import com.example.simplerecipes.domain.entity.CategoryItem
import com.example.simplerecipes.presentation.ui.categories.dispatchers.CategoryEventDispatcher

class CategoryItemsAdapter(
    private val items: List<CategoryItem>,
    private val eventDispatcher: CategoryEventDispatcher
) : RecyclerView.Adapter<CategoryItemsAdapter.CategoryItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCategoryBinding.inflate(inflater, parent, false)
        return CategoryItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CategoryItemsViewHolder(
        private val binding: ViewCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryItem) {
            with(binding) {
                tvCategoryName.text = item.name
                ivCategoryItem.load(item.imageUrl) {
                    placeholder(R.drawable.ic_food_placeholder)
                    error(R.drawable.ic_error)
                }
                cardCategoryDetail.setOnClickListener {
                    eventDispatcher.onCategoryPressed(item, cardCategoryDetail)
                }
            }
        }
    }
}
