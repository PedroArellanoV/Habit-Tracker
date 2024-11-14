package com.pedro.habittracker.ui.addedithabit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedro.habittracker.R
import com.pedro.habittracker.databinding.FragmentAddedithabitBinding

class AddEditHabitFragment : Fragment() {

    private val _binding: FragmentAddedithabitBinding? = null
    private val binding get()  = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addedithabit, container, false)
    }

}