package com.example.challengeempat.room

import androidx.room.*

@Dao
interface NoteDAO {

    @Insert
    fun insertNote(note: DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id ASC ")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote)

    @Update
    fun updateNote(note: DataNote)

}