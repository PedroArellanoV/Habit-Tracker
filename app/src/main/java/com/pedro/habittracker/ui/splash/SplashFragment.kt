package com.pedro.habittracker.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pedro.habittracker.R
import com.pedro.habittracker.databinding.FragmentSplashBinding
import com.pedro.habittracker.ui.utils.FirebaseAuthSingleton


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        object: CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                initAuthentication()
            }
        }.start()
    }

    private fun initAuthentication(){
        val currentUser = FirebaseAuthSingleton.auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
        }
    }

}