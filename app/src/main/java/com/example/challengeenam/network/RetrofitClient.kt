@file:Suppress("unused")

package com.example.challengeenam.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL2 = "https://api.themoviedb.org/3/"

    val instance: ResultApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ResultApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): ResultApi = retrofit.create(ResultApi::class.java)
}