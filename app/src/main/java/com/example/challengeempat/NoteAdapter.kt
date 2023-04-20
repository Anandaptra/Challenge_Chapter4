package com.example.challengeempat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeempat.databinding.ItemNoteBinding
import com.example.challengeempat.room.DataNote
import com.example.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class NoteAdapter(var listNote : List<DataNote>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var DBNote : NoteDatabase? = null

    class ViewHolder(var binding : ItemNoteBinding):RecyclerView.ViewHolder(binding.root) {
//        fun bindNote (itemData : DataNote) {
//            binding.note = itemData
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
//        holder.binding.noteId.text = listNote[position].id.toString()
//        holder.binding.noteTitle.text = listNote[position].content

        holder.binding.note = listNote[position]

        holder.binding.btnEditNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("dataedit", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment, bundle)
        }

        holder.binding.btnDeleteNote.setOnClickListener {
            DBNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                val del = DBNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as HomeFragment).activity?.runOnUiThread{
                    (holder.itemView.context as HomeFragment).getAllNote()
                }
            }
            Navigation.findNavController(it).navigate(R.id.homeFragment)

            Toast.makeText(it.context, "Data Berhasil di Hapus", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return  listNote.size
    }

    fun setNoteData(listNote: ArrayList<DataNote>)
    {
        this.listNote=listNote
//        notifyDataSetChanged()
    }
}