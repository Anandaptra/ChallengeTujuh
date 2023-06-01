package com.example.challengeenam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Query("SELECT * FROM FavNote")
    fun getAllMovieFavorit() : List<FavNote>

//    @Insert
//    suspend fun insertFilmFavorites(filmFavorites: FavNote)

    @Insert
    suspend fun addToFavorit(favoritMovie : FavNote) : Long
    @Query("SELECT count(*) FROM FavNote WHERE FavNote.id = :id")
    fun checkMovie(id: Int) : Int
    @Delete
    suspend fun deleteFilmFavorit(filmFavorites: FavNote) : Int


}