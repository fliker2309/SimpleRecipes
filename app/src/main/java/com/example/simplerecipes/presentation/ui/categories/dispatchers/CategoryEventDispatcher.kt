package com.example.simplerecipes.presentation.ui.categories.dispatchers

import android.view.View
import com.example.simplerecipes.domain.entity.CategoryItem

interface CategoryEventDispatcher {
    fun onCategoryPressed(category: CategoryItem, view: View)
}