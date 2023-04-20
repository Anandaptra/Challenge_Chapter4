package com.example.challengeempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengeempat.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var splash: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)

        Handler(Looper.myLooper()!!).postDelayed({

            splash = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
            if (splash.getString("username", "").equals("")) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else if (splash.getString("username", "")!!.isNotEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 2500)
        return binding.root
    }

}