package com.example.challengeempat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengeempat.databinding.FragmentAddBinding
import com.example.challengeempat.room.DataNote
import com.example.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class AddFragment : Fragment() {
    lateinit var binding: FragmentAddBinding
    var dbNote : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote =NoteDatabase.getInstance(requireContext())

        binding.btnSaveNote.setOnClickListener{
            addNote()
        }
    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note = binding.noteContent.text.toString()

            dbNote!!.noteDao().insertNote(DataNote(0,title, note))
        }
        findNavController().navigate(R.id.action_addFragment_to_homeFragment)
//        activity?.finish()
    }

}