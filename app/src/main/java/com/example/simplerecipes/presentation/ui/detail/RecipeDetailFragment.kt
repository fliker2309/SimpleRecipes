package com.example.simplerecipes.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplerecipes.databinding.FragmentDetailRecipeBinding
import com.example.simplerecipes.presentation.ui.detail.adapters.RecipeIngredientsAdapter
import com.example.simplerecipes.presentation.ui.detail.adapters.RecipeStepsAdapter

class RecipeDetailFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by activityViewModels()
    private val args: RecipeDetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailRecipeBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private lateinit var ingredientsAdapter: RecipeIngredientsAdapter
    private lateinit var stepsAdapter: RecipeStepsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args.recipeId.let {
            viewModel.getRecipeDetails(it.toInt())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredientsAdapter = RecipeIngredientsAdapter()
        stepsAdapter = RecipeStepsAdapter()
        val ingredientLayoutManager = LinearLayoutManager(requireContext())
        val stepsLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        with(binding) {
            rvIngredients.adapter = ingredientsAdapter
            rvIngredients.layoutManager = ingredientLayoutManager
            rvSteps.adapter = stepsAdapter
            rvSteps.layoutManager = stepsLayoutManager
        }
    }
}
