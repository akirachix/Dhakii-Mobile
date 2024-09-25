package com.akirachix.mamamindtrial.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.R

class CHPAdapter(private val chpList: List<CHP>) : RecyclerView.Adapter<CHPAdapter.CHPViewHolder>() {

    class CHPViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTextView)
        val regNo: TextView = view.findViewById(R.id.regNoTextView)
        val hospital: TextView = view.findViewById(R.id.hospitalTextView)
        val location: TextView = view.findViewById(R.id.locationTextView)
        val subLocation: TextView = view.findViewById(R.id.subLocationTextView)
        val village: TextView = view.findViewById(R.id.villageTextView)
        val mothers: TextView = view.findViewById(R.id.mothersTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CHPViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chpdetail_item, parent, false)
        return CHPViewHolder(view)
    }

    override fun onBindViewHolder(holder: CHPViewHolder, position: Int) {
        val chp = chpList[position]
//        holder.name.text = chp.name
        holder.regNo.text = chp.reg_no
//        holder.hospital.text = chp.hospital
        holder.location.text = chp.location
        holder.subLocation.text = chp.sub_location
        holder.village.text = chp.village
//        holder.mothers.text = chp.number_of_mothers.toString()
    }

    override fun getItemCount() = chpList.size
}
