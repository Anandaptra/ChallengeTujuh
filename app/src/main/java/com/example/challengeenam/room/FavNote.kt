package com.example.challengeenam.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavNote(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "tanggal")
    var tanggal: String,
    @ColumnInfo(name = "poster_path")
    val poster_path:String,
    @ColumnInfo(name = "overview")
    var overview: String
    ) : Parcelable
