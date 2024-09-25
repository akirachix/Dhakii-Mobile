package com.akirachix.mamamindtrial.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.MotherItemBinding

class MothersAdapter(
    private var mothersList: List<String>,
    private val viewColor: Int // Color for the "View" text
) : RecyclerView.Adapter<MothersAdapter.MotherViewHolder>() {

    // ViewHolder class now uses View Binding
    class MotherViewHolder(val binding: MotherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotherViewHolder {
        // Inflate the layout using View Binding
        val binding = MotherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MotherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MotherViewHolder, position: Int) {
        val name = mothersList[position]
        // Set mother's name
        holder.binding.motherName.text = name
        // Set "View" text
        holder.binding.viewButton.text = "View"

        // Set the text color passed from the fragment
        holder.binding.viewButton.setTextColor(viewColor)

        // Optionally handle click events if you want to perform an action on "View"
        holder.binding.viewButton.setOnClickListener {
            // Implement any navigation logic if required
        }
    }

    override fun getItemCount(): Int {
        return mothersList.size
    }

    fun updateMothers(newMothers: List<String>) {
        mothersList = newMothers
        notifyDataSetChanged() // Refresh the adapter
    }
}