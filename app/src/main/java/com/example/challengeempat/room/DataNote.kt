package com.example.challengeempat.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity
@Parcelize
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
): Parcelable

