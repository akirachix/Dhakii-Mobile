package com.akirachix.mamamindtrial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the user's details in the appropriate TextViews
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val regNoTextView = view.findViewById<TextView>(R.id.regNoTextView)
        val hospitalTextView = view.findViewById<TextView>(R.id.hospitalTextView)
        val locationTextView = view.findViewById<TextView>(R.id.locationTextView)
        val subLocationTextView = view.findViewById<TextView>(R.id.subLocationTextView)
        val villageTextView = view.findViewById<TextView>(R.id.villageTextView)
        val mothersTextView = view.findViewById<TextView>(R.id.mothersTextView)

        // Example data - ideally, this should be passed dynamically
        nameTextView.text = "MarieNana Momoa"
        regNoTextView.text = "30266-00100"
        hospitalTextView.text = "Kamukunji"
        locationTextView.text = "Pumwani Hospital"
        subLocationTextView.text = "Eastleigh"
        villageTextView.text = "Maina Wanjigi"
        mothersTextView.text = "50"
    }
}
