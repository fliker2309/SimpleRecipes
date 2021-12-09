package com.example.simplerecipes.presentation.ui.favorites

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simplerecipes.databinding.FragmentFavoritesBinding
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.favorites.adapter.FavoritesAdapter
import com.example.simplerecipes.presentation.ui.search.adapters.RecipeEventDispatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), RecipeEventDispatcher {

    private val viewModel: FavoritesViewModel by viewModels()
    private var favoriteRecipes = mutableListOf<Recipe>()
    private lateinit var adapter: FavoritesAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        adapter = FavoritesAdapter(this)
        with(binding) {
            rvFavoriteRecipes.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvFavoriteRecipes.adapter = adapter
        }
    }

    private fun initObservers() {
        with(binding) {
            viewModel.favRecipes.observe(
                viewLifecycleOwner,
                Observer { recipes ->
                    if (recipes.isEmpty()) {
                        rvFavoriteRecipes.isVisible = false
                        emptyLayout.viewEmptyFavorites.isVisible = true
                    } else {
                        rvFavoriteRecipes.isVisible = true
                        emptyLayout.viewEmptyFavorites.isVisible = false
                        favoriteRecipes = recipes.toMutableList()
                        adapter.submitList(favoriteRecipes)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecipePressed(recipe: Recipe, view: View) {
        if (isNetworkAvailable()) {
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(recipe.id.toString())
            this.findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val context = requireContext()
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
