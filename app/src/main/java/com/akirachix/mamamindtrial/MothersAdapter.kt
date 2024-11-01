package com.akirachix.mamamindtrial.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.VisitStatus
import com.akirachix.mamamindtrial.databinding.MotherItemBinding

class MothersAdapter(
    private var mothersList: MutableList<MotherDetail>,
    private val viewColor: Int,
    private val onMotherVisited: (MotherDetail) -> Unit
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

        // Set button text and state based on visit status
        if (mother.visitStatus == VisitStatus.VISITED) {
            holder.binding.viewButton.text = "Visited"
            holder.binding.viewButton.isEnabled = false // Disable button for visited mothers
        } else {
            holder.binding.viewButton.text = "View"
            holder.binding.viewButton.isEnabled = true
        }

        // Set button click listener
        holder.binding.viewButton.setOnClickListener {
            if (mother.visitStatus != VisitStatus.VISITED) {
                onMotherVisited(mother)
                mother.visitStatus = VisitStatus.VISITED // Set the status to visited when clicked
                notifyDataSetChanged()
            }
        }

        // Set button color
        holder.binding.viewButton.setTextColor(viewColor)
    }

    override fun getItemCount(): Int = mothersList.size

    fun updateMothers(newMothers: List<MotherDetail>) {
        mothersList.clear()
        mothersList.addAll(newMothers)
        notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_CODE_QUESTIONS = 1
    }
}