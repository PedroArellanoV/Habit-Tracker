package com.pedro.habittracker.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.ktx.Firebase
import com.pedro.habittracker.R
import com.pedro.habittracker.databinding.FragmentHomeBinding
import com.pedro.habittracker.databinding.FragmentLogInBinding
import com.pedro.habittracker.utils.FirebaseAuthSingleton


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {


        binding.btnSignOut.setOnClickListener {
            FirebaseAuthSingleton.auth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_signUpFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}