package com.example.challengeempat

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.challengeempat.databinding.FragmentEditBinding
import com.example.challengeempat.room.DataNote
import com.example.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    var dbNote: NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = NoteDatabase.getInstance(requireContext())

        // var getDataNote = activity?.intent?.getSerializableExtra("dataedit") as DataNote
        var getDataNote = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("dataedit", DataNote::class.java)
        } else {
            arguments?.getParcelable("dataedit")
        }

        if (getDataNote != null) {
            binding.editTitle.setText(getDataNote.title)
        }
        if (getDataNote != null) {
            binding.editNotee.setText(getDataNote.content)
        }
        if (getDataNote != null) {
            binding.idNote.setText(getDataNote.id.toString())
        }

        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTitle.text.toString()
        var note = binding.editNotee.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(DataNote(idNote, title, note))

        }
        Toast.makeText(requireContext(), "Dat berhasil di Edit", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
    }

}