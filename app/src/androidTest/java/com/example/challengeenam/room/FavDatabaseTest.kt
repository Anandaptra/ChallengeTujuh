package com.example.challengeenam.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.challengeenam.FavoriteDammy
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

class FavDatabaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: FavDatabase
    private lateinit var dao: FavDao
    private val sampleFavorite = FavoriteDammy.generateDummyFavourite()[0]

    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavDatabase::class.java
        ).build()
        dao = database.favoritDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun saveFavouriteSuccess() = runTest {
        dao.addToFavorit(sampleFavorite)
        val actualFavorite = dao.getAllMovieFavorit()
        Assert.assertEquals(sampleFavorite.title, actualFavorite[0].title)
        println(sampleFavorite.title)
        println(actualFavorite[0].title)
    }

    @Test
    fun deleteFav() = runTest {
        dao.addToFavorit(sampleFavorite)
        dao.deleteFilmFavorit(sampleFavorite)
        val actualNews = dao.getAllMovieFavorit()
        Assert.assertTrue(actualNews.isEmpty())
    }
}