package com.example.challengeempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.challengeempat.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var sharedpref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedpref = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        binding.btnSignin.setOnClickListener {
            var getUsername = sharedpref.getString("username", "")
            var getPassword = sharedpref.getString("password", "")

            var username = binding.etUsername.text.toString()
            var password = binding.etPassword.text.toString()

            if (username == getUsername && password.equals(getPassword)) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Akun Belum Terdaftar", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.txtsignup.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_regisFragment)
        }
    }

}