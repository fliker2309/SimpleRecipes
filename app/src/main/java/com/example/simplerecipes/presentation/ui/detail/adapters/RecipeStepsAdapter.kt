package com.example.simplerecipes.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecipes.databinding.StepItemBinding
import com.example.simplerecipes.domain.entity.Instruction

class RecipeStepsAdapter :
    ListAdapter<Instruction, RecipeStepsAdapter.StepsViewHolder>(StepDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitSteps(newSteps: List<Instruction>) {
        submitList(newSteps)
    }

    class StepsViewHolder(private val binding: StepItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): StepsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = StepItemBinding.inflate(inflater, parent, false)
                return StepsViewHolder(binding)
            }
        }

        fun bind(step: Instruction) {
            binding.tvNumber.text = step.number.toString()
            binding.tvStep.text = step.step
        }
    }

    class StepDiffCallback : DiffUtil.ItemCallback<Instruction>() {
        override fun areItemsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
            return oldItem == newItem
        }
    }
}
