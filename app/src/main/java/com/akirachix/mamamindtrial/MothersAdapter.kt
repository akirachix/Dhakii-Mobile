package com.akirachix.mamamindtrial.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.databinding.MotherItemBinding

class MothersAdapter(
    private var mothersList: List<String>,
    private val viewColor: Int
) : RecyclerView.Adapter<MothersAdapter.MotherViewHolder>() {

    class MotherViewHolder(val binding: MotherItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotherViewHolder {
        val binding = MotherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MotherViewHolder(binding)


    }

    override fun onBindViewHolder(holder: MotherViewHolder, position: Int) {
        val name = mothersList[position]

        holder.binding.motherName.text = name

        holder.binding.viewButton.text = "View"


        holder.binding.viewButton.setTextColor(viewColor)


        holder.binding.viewButton.setOnClickListener {

        }
        holder.binding.viewButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Questions::class.java)
            context.startActivity(intent)
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