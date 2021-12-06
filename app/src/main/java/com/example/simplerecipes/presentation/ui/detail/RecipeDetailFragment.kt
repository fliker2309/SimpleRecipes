package com.example.simplerecipes.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.simplerecipes.R
import com.example.simplerecipes.databinding.FragmentDetailRecipeBinding
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.presentation.ui.detail.adapters.RecipeIngredientsAdapter
import com.example.simplerecipes.presentation.ui.detail.adapters.RecipeStepsAdapter
import kotlinx.coroutines.launch

private const val TAG = "recipeDetailsTag"

class RecipeDetailFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by activityViewModels()
    private val args: RecipeDetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailRecipeBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args.recipeId.let {
            viewModel.getRecipeDetailsFromNetwork(it.toInt())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        initRecyclers()
        initListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclers() {
        binding.rvIngredients.apply {
            adapter = RecipeIngredientsAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvSteps.apply {
            adapter = RecipeStepsAdapter()
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeObservers() {
        viewModel.recipe.observe(viewLifecycleOwner) { recipe: Recipe ->
            showRecipeData(recipe)
        }
    }

    private fun showRecipeData(recipe: Recipe) {
        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                ivCurrentRecipeDetail.load(recipe.imageUrl) {
                    placeholder(R.drawable.ic_food_placeholder)
                    error(R.drawable.ic_error)
                }
                tvRecipeName.text = recipe.title
                recipe.ingredients?.let {
                    (rvIngredients.adapter as RecipeIngredientsAdapter).submitIngredients(
                        it
                    )
                }
                recipe.instructions?.let {
                    (rvSteps.adapter as RecipeStepsAdapter).submitSteps(
                        it
                    )
                }
            }
        }
    }
}
