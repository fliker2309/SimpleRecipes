package com.example.simplerecipes.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.StepItemBinding
import com.example.simplerecipes.domain.entity.Instruction

class RecipeStepsAdapter : RecyclerView.Adapter<RecipeStepsAdapter.StepsViewHolder>() {

    var steps: List<Instruction> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount(): Int = steps.size

    fun submitSteps(newSteps: List<Instruction>) {
        steps = newSteps
        notifyDataSetChanged()
    }

    class StepsViewHolder(private val binding: StepItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): StepsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = StepItemBinding.inflate(inflater, parent, false)
                return StepsViewHolder(binding)
            }
        }

        fun bind(step: Instruction) {
            with(binding) {
                tvNumber.text = step.number.toString()
                tvStep.text = step.step
            }
        }
    }
}
