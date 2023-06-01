package com.example.challengeenam.module

import android.app.Application
import androidx.room.Room
import com.example.challengeenam.room.FavDao
import com.example.challengeenam.room.FavDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): FavDatabase {
        return Room.databaseBuilder(app, FavDatabase::class.java, "favorite_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(database:FavDatabase): FavDao {
        return database.favoritDao()
    }
}