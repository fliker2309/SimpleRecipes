package com.example.simplerecipes.presentation.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simplerecipes.databinding.FragmentSearchBinding
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.search.adapters.RecipeEventDispatcher
import com.example.simplerecipes.presentation.ui.search.adapters.RecipePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), RecipeEventDispatcher {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var pagingAdapter: RecipePagingAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isNetworkAvailable()) {
            initView()
            initObservers()
            setupListeners()
        } else {
            showNoInternetUI()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        pagingAdapter = RecipePagingAdapter(this)
        with(binding) {
            foundedRecipesRecyclerView.isVisible = true
            txtRecipeSearchLayout.isVisible = true
            errorLayout.btnRetry.isVisible = false
            errorLayout.tvErrorTitle.isVisible = false
            foundedRecipesRecyclerView.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            foundedRecipesRecyclerView.adapter = pagingAdapter
            pagingAdapter.addLoadStateListener { state: CombinedLoadStates ->
                binding.foundedRecipesRecyclerView.isVisible = state.refresh != LoadState.Loading
                binding.progressBar.isVisible = state.refresh == LoadState.Loading
            }
        }
    }

    private fun requestSearch(shouldRetry: Boolean = false) {
        val text = binding.txtRecipeSearch.text.toString()
        if (text.trim().isNotEmpty() || shouldRetry) {
            viewModel.query = text
            pagingAdapter.refresh()
        }
    }

    private fun setupListeners() {
        with(binding) {
            txtRecipeSearch.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    dismissKeyboard(v)
                    requestSearch()
                    return@setOnKeyListener true
                }
                false
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipesFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest {
                if (it.append is LoadState.Error) {
                    pagingAdapter.retry()
                }
            }
        }
    }

    private fun dismissKeyboard(view: View) {
        val inputMethodManager = ContextCompat.getSystemService(
            requireContext(), InputMethodManager::class.java
        )
        view.clearFocus()
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onRecipePressed(recipe: Recipe, view: View) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(recipe.id.toString())
        this.findNavController().navigate(action)
    }

    private fun showNoInternetUI() {
        with(binding) {
            foundedRecipesRecyclerView.isVisible = false
            progressBar.isVisible = false
            txtRecipeSearchLayout.isVisible = false
            errorLayout.btnRetry.isVisible = true
            errorLayout.tvErrorTitle.isVisible = true

            errorLayout.btnRetry.setOnClickListener {
                if (isNetworkAvailable()) {
                    initView()
                    initObservers()
                    setupListeners()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No Internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
