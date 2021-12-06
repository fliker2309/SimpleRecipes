package com.example.simplerecipes.presentation.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplerecipes.databinding.FragmentCategoriesBinding
import com.example.simplerecipes.domain.entity.CategoryItem
import com.example.simplerecipes.presentation.ui.categories.adapters.CategoriesAdapter
import com.example.simplerecipes.presentation.ui.categories.dispatchers.CategoryEventDispatcher
import com.example.simplerecipes.presentation.ui.categories.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment(), CategoryEventDispatcher {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private lateinit var adapter: CategoriesAdapter
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoriesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        subscribeObservers()
    }

    private fun initRecycler() {
        with(binding) {
            rvCategories.layoutManager = LinearLayoutManager(requireContext())
            rvCategories.adapter = adapter
        }
    }

    private fun subscribeObservers() {
        viewModel.categories.observe(
            viewLifecycleOwner, { categories ->
                adapter.submitList(categories)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryPressed(category: CategoryItem, view: View) {
        val extras = FragmentNavigatorExtras(view to category.name)
        val action =
            CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDetailFragment(category)
        findNavController().navigate(action, extras)
    }
}
