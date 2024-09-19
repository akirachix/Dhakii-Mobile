package com.akirachix.mamamindtrial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.databinding.ItemExerciseBinding

class ExercisesAdapter(private val exercises: List<ExerciseItem>) :
    RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: ExerciseItem) {
            binding.exerciseTitle.text = exercise.title
            binding.exerciseImage.setImageResource(exercise.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    override fun getItemCount() = exercises.size
}

data class ExerciseItem(val title: String, val imageResId: Int)