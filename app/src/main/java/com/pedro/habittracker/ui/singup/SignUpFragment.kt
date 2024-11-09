package com.pedro.habittracker.ui.singup

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pedro.habittracker.R
import com.pedro.habittracker.data.User
import com.pedro.habittracker.databinding.FragmentSignUpBinding
import com.pedro.habittracker.utils.FirebaseAuthSingleton


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

        binding.btnSignUp.setOnClickListener {
            signupAuthentication()
        }
        binding.tvLogin.setOnClickListener { findNavController().navigate(R.id.action_signUpFragment_to_logInFragment) }
    }

    private fun signupAuthentication() {
        val username: String? = binding.tiName.text?.toString()
        val email: String? = binding.tiEmail.text?.toString()
        val password: String? = binding.tiPassword.text?.toString()
        val confirmPassword: String? = binding.tiConfirmPassword.text?.toString()

        binding.tiName.addTextChangedListener {
            binding.etName.error = null
        }
        binding.tiEmail.addTextChangedListener {
            binding.etEmail.error = null
        }
        binding.tiPassword.addTextChangedListener {
            binding.etPassword.error = null
        }
        binding.tiConfirmPassword.addTextChangedListener {
            binding.etConfirmPassword.error = null
        }

        if (!username.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty() && !confirmPassword.isNullOrEmpty()) {
            if (password == confirmPassword) {
                val user = User(username, email, password)
                firebaseAuth(user)
            } else {
                binding.etConfirmPassword.error = "Password doesn't match"
            }
        } else {
            when{
                username.isNullOrEmpty() -> binding.etName.error = "Uncompleted field"

                email.isNullOrEmpty() -> binding.etEmail.error = "Uncompleted field"

                password.isNullOrEmpty() -> binding.etPassword.error = "Uncompleted field"

                confirmPassword.isNullOrEmpty() -> binding.etEmail.error = "Uncompleted field"
            }
        }
    }

    private fun firebaseAuth(user: User) {
        val auth = FirebaseAuthSingleton.auth
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("FIREBASE AUTHENTICATION", "userRegister: Success")
                    findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                } else {
                    Log.i("FIREBASE AUTHENTICATION", "userRegister: Failed")
                    createDialog()
                }
            }
    }

    private fun createDialog(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("The information provided in the registration form is invalid. Please check your details and try again.")
        builder.setPositiveButton("OK"){dialog, _->
            dialog.cancel()
        }
        builder.show().create()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}