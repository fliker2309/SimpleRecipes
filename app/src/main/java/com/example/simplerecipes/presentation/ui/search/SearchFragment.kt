package com.example.simplerecipes.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

    companion object {
        const val TAG = "SearchFragment"
    }

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
        pagingAdapter = RecipePagingAdapter(this)
        binding.foundedRecipesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.foundedRecipesRecyclerView.adapter = pagingAdapter
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipesFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
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
        Log.d(TAG, "go to ${recipe.id}")
    }
}
