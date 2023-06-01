package com.example.challengeenam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("unused")
@Database(
    entities = [FavNote::class],
    version = 2
)

abstract class FavDatabase : RoomDatabase() {

    abstract fun favoritDao() : FavDao

    companion object{
        private var INSTANCE : FavDatabase? = null

        fun getInstance(context : Context): FavDatabase? {
            if (INSTANCE == null){
                synchronized( FavDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavDatabase::class.java,"favorit.db").build()
                }
            }
            return INSTANCE
        }

    }

}