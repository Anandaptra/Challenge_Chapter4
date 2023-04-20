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
import com.example.challengeempat.databinding.FragmentRegisBinding


class RegisFragment : Fragment() {
    lateinit var binding: FragmentRegisBinding
    lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        binding.btnRegis.setOnClickListener {
            val name = binding.etNama.text.toString()
            val username = binding.etUser.text.toString()
            val password = binding.etPw.text.toString()

            val regist = pref.edit()
            regist.putString("nama", name)
            regist.putString("username", username)
            regist.putString("password", password)
            regist.apply()
            Toast.makeText(context,"Registrasi Anda Sukses", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).navigate(R.id.action_regisFragment_to_loginFragment)

        }


    }

}