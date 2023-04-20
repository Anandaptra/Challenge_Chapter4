package com.example.challengeempat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeempat.databinding.FragmentHomeBinding
import com.example.challengeempat.room.DataNote
import com.example.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var noteViewModel : NoteViewModel
    lateinit var shredPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shredPref= requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
        _binding?.Username?.text = "Welcome,  " + shredPref.getString("username", "name")

        NoteDB = NoteDatabase.getInstance(requireContext())

        noteVm()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            adapterNote.setNoteData(it as ArrayList<DataNote>)

        })

        binding.btnAddNote.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        binding.logout.setOnClickListener {
            val dataPref = shredPref.edit()
            dataPref.clear()
            dataPref.apply()
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    fun noteVm(){
        adapterNote = NoteAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapterNote
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()

            activity?.runOnUiThread{
                adapterNote = NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }

        }
    }

}