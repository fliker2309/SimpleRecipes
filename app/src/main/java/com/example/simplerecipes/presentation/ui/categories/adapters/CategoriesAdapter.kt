/*
package com.example.simplerecipes.presentation.ui.categories.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simplerecipes.databinding.ViewCategoriesListBinding
import com.example.simplerecipes.domain.entity.Category
import com.example.simplerecipes.presentation.ui.categories.dispatchers.CategoryEventDispatcher

class CategoriesAdapter(
    private val categories: List<Category>,
    private val eventDispatcher: CategoryEventDispatcher,
    private val context: Context
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCategoriesListBinding.inflate(inflater,parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(
        private val binding: ViewCategoriesListBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(category: Category) {
            val adapter = CategoryItemsAdapter(category.items, eventDispatcher)
            with(binding) {
              ivCategory.load(category.)
            }
        }
    }
}
*/
