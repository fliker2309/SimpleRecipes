package com.example.simplerecipes.presentation.ui.categories.adapters

import android.os.Parcelable
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.domain.entity.Category
import com.example.simplerecipes.presentation.ui.categories.dispatchers.CategoryEventDispatcher

class CategoriesAdapter(
    private val eventDispatcher: CategoryEventDispatcher
) : ListAdapter<Category, CategoriesAdapter.CategoriesViewHolder>(CategoryDiffCallback()) {


    private val scrollStates = hashMapOf<String, Parcelable?>()

    override fun onViewRecycled(holder: CategoriesViewHolder) {
        super.onViewRecycled(holder)
        val item = getItem(holder.bindingAdapterPosition)
        val key = item.name
        scrollStates[key] = TODO()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }



    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }


}
