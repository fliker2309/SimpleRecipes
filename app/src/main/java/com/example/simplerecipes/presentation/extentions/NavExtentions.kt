/*
package com.example.simplerecipes.presentation.extentions

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.simplerecipes.R
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.detail.RecipeDetailViewModel
import com.example.simplerecipes.presentation.ui.search.SearchFragmentDirections


private const val TAG = "myTag"
fun Fragment.findNavController(): NavController {
    return Navigation.findNavController(
        requireActivity(),
        R.id.navHostFragmentActivityMain
    )
}

fun Fragment.navigateToRecipeDetail(
    recipe: Recipe,
    view: View,
    isFavorite: Boolean = false
) {
    val viewModel by activityViewModels<RecipeDetailViewModel>()
    val extras = FragmentNavigatorExtras(view to recipe.id.toString())
    if (isFavorite) {
        viewModel.presentRecipeDetails(recipe)
    } else {
        viewModel.presentRecipeDetails(recipe)
      */
/*  viewModel.getRecipeDetails(recipe.id)*//*

    }
    val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(recipe.id.toString())
    Log.d(TAG, "Click on the ${recipe.id} recipe")
    findNavController().navigate(action, extras)
}
*/
