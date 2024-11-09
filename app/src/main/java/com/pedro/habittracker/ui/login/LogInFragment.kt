package com.pedro.habittracker.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.pedro.habittracker.R
import com.pedro.habittracker.databinding.FragmentLogInBinding
import com.pedro.habittracker.utils.FirebaseAuthSingleton


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

    }

    private fun initUI() {

        binding.btnLogin.setOnClickListener {
            loginAuthentication()
        }
    }

    private fun loginAuthentication() {
        val email = binding.tiEmail.text?.toString()
        val password = binding.tiPassword.text?.toString()

        binding.tiEmail.addTextChangedListener { binding.etEmail.error = null }
        binding.tiPassword.addTextChangedListener { binding.etPassword.error = null }

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
            FirebaseAuthSingleton.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.i("AUTHENTICATION", "SUCCESS")
                        findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                    }else{
                        Log.i("AUTHENTICATION", task.exception.toString())
                        createDialog()
                    }
                }
        }else{
            when {
                email.isNullOrEmpty() -> binding.etEmail.error = "Uncompleted field"
                password.isNullOrEmpty() -> binding.etPassword.error = "Uncompleted field"
            }
        }
    }


    private fun createDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("The provided information does not match any registered user.")
        builder.setPositiveButton("OK"){dialog, _->
            dialog.cancel()
        }
        builder.show().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}