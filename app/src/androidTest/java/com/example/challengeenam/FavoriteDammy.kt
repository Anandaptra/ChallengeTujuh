package com.example.challengeenam

import com.example.challengeenam.room.FavNote

object FavoriteDammy {
    fun generateDummyFavourite():List<FavNote>{
        val favoriteList = ArrayList<FavNote>()
        for (i in 0..5){
            val favorite = FavNote(
                id = i,
                title = "title $i",
                tanggal = "2022-03-0$i",
                poster_path = "/$i.jpg",
                overview = "overview $i"
            )
            favoriteList.add(favorite)
        }
        return favoriteList
    }
}