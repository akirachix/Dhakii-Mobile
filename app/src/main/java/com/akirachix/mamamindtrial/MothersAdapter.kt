package com.postman.mamamind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.databinding.MotherItemBinding


class MothersAdapter(private val mothersList: List<String>) : RecyclerView.Adapter<MothersAdapter.MotherViewHolder>() {

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
        holder.binding.nameTextView.text = name
        // Set "View" text
        holder.binding.viewTextView.text = "View"
    }

    override fun getItemCount(): Int {
        return mothersList.size
    }
}
