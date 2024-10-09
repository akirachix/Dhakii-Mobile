package com.akirachix.mamamindtrial.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.databinding.MotherItemBinding

class MothersAdapter(
    private var mothersList: MutableList<MotherDetail>, // Make sure this handles MotherDetail
    private val viewColor: Int,
    private val onMotherVisited: (MotherDetail) -> Unit // Callback function to notify when a mother is visited
) : RecyclerView.Adapter<MothersAdapter.MotherViewHolder>() {

    class MotherViewHolder(val binding: MotherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotherViewHolder {
        val binding = MotherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MotherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MotherViewHolder, position: Int) {
        val mother = mothersList[position]

        // Set mother's name
        holder.binding.motherName.text = "${mother.firstName} ${mother.lastName}"

        // Set the button text to "Visited" or "View" based on the visit status, but keep it always clickable
        val currentTime = System.currentTimeMillis()
        val twoWeeksInMillis = 14 * 24 * 60 * 60 * 1000L

        if (mother.lastVisitDate != null && currentTime - mother.lastVisitDate!!.time < twoWeeksInMillis) {
            holder.binding.viewButton.text = "Visited" // Change the text to "Visited"
        } else {
            holder.binding.viewButton.text = "View" // Default to "View"
        }

        // Make the button always clickable
        holder.binding.viewButton.isEnabled = true // Ensure the button is always enabled
        holder.binding.viewButton.setOnClickListener {
            // Ensure the click event is set properly

            // Start the QuestionsActivity when the button is clicked
            val context = holder.itemView.context
            val intent = Intent(context, Questions::class.java)
            intent.putExtra("mother_name", mother.firstName) // Pass the mother's name
            (context as Activity).startActivityForResult(intent, MothersAdapter.REQUEST_CODE_QUESTIONS) // Start the activity for a result
        }

        holder.binding.viewButton.setTextColor(viewColor)
    }
    override fun getItemCount(): Int = mothersList.size

    fun updateMothers(newMothers: List<MotherDetail>) {
        mothersList.clear()
        mothersList.addAll(newMothers)
        notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_CODE_QUESTIONS = 1 // Define the request code here
    }
}

