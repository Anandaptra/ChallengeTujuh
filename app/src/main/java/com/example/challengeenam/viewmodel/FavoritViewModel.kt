package com.example.challengeenam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengeenam.room.FavDao
import com.example.challengeenam.room.FavDatabase
import com.example.challengeenam.room.FavNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused")
@HiltViewModel
class FavoritViewModel @Inject constructor(app: Application): AndroidViewModel(app){
    private var favDao : FavDao?=null
    private var favDb : FavDatabase?=null

    private var liveDataListfav: MutableLiveData<List<FavNote>> = MutableLiveData()

    init {
        getAllMoviePopular()
    }

    fun getliveDataMoviefav(): MutableLiveData<List<FavNote>> {
        return  liveDataListfav
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllMoviePopular() {

        GlobalScope.launch {
            val favdao = FavDatabase.getInstance(getApplication())!!.favoritDao()
            val listNote = favdao.getAllMovieFavorit()
            liveDataListfav.postValue(listNote)

        }
    }

    suspend fun delete(favoritMovie : FavNote) {
        val dataDao = FavDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.deleteFilmFavorit(favoritMovie)
        getAllMoviePopular()
    }

    suspend fun insert(favoritMovie : FavNote){
        val dataDao = FavDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.addToFavorit(favoritMovie)
        getAllMoviePopular()
    }

    fun check(id: Int){
        val dataDao = FavDatabase.getInstance(getApplication())!!.favoritDao()
        dataDao.checkMovie(id)
        getAllMoviePopular()
    }


}