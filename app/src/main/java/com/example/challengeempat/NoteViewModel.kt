package com.example.challengeempat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengeempat.room.DataNote
import com.example.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app : Application): AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<DataNote>>

    init{
        allNote = MutableLiveData()
        getAllNote()
    }
    fun getAllNoteObservers(): MutableLiveData<List<DataNote>> {
        return allNote
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }
}