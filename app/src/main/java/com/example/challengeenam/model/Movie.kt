package com.example.challengeenam.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    val id : Int,
    val title:String,
    val overview:String,
    val releaseDate:String,
    val imagePath:String
): Parcelable