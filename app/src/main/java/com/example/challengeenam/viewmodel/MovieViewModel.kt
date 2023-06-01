@file:Suppress("unused", "unused", "unused", "unused")

package com.example.challengeenam.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeenam.model.ResponseMovie
import com.example.challengeenam.network.ResultApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.challengeenam.model.Result
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate", "JoinDeclarationAndAssignment")
@HiltViewModel
class MovieViewModel @Inject constructor(private var api : ResultApi): ViewModel() {
    lateinit var liveDataMovie : MutableLiveData<List<Result>>
    private lateinit var liveDetailMovie : MutableLiveData<ResponseMovie>

    private val key = "a89633b1333a8e0f2bb90016feb3252a"

    init {
        liveDataMovie = MutableLiveData()
        liveDetailMovie = MutableLiveData()
    }

    fun getDataFilm() : MutableLiveData<List<Result>> {
        return liveDataMovie
    }
    fun getDetailMovie() : MutableLiveData<ResponseMovie> {
        return liveDetailMovie
    }

    fun getApiMovie() {
        api.getTrendingMovie(key).enqueue(object :
            Callback<ResponseMovie> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<ResponseMovie>,
                response: Response<ResponseMovie>
            ) {
                if (response.isSuccessful){
                    liveDataMovie.postValue(response.body()!!.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                liveDataMovie.postValue(null)
            }

        })
    }

}