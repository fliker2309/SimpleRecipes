package com.example.simplerecipes.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.StepItemBinding
import com.example.simplerecipes.domain.entity.Instruction

class RecipeStepsAdapter : RecyclerView.Adapter<RecipeStepsAdapter.StepsViewHolder>() {

    var steps: List<Instruction> = emptyList()
        set(newValue) {
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StepItemBinding.inflate(inflater, parent, false)
        return StepsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val step = steps[position]
        with(holder.binding) {
            tvNumber.text = step.number.toString()
            tvStep.text = step.step
        }
    }

    override fun getItemCount(): Int = steps.size

    class StepsViewHolder(val binding: StepItemBinding) : RecyclerView.ViewHolder(binding.root)
}
