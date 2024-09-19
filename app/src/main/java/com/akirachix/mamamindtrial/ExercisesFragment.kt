package com.akirachix.mamamindtrial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.FragmentExercisesBinding

class ExercisesFragment : Fragment() {
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercisesList = listOf(
            ExerciseItem("Workouts for postpartum strength and flexibility", R.drawable.exercise1),
            ExerciseItem("Healing the postpartum belly with gentle core workouts", R.drawable.exercise2),
            ExerciseItem("Importance of core exercises after pregnancy", R.drawable.exercise3),
            ExerciseItem("Importance of core exercises after pregnancy", R.drawable.exercise4),
            ExerciseItem("Healing the postpartum belly with gentle core workouts", R.drawable.exercise1),

            )

        binding.exercisesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.exercisesRecyclerView.adapter = ExercisesAdapter(exercisesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}