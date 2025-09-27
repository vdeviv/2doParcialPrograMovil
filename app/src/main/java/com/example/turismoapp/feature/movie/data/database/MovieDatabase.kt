package com.example.turismoapp.feature.movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LikedMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun likedMovieDao(): LikedMovieDao
}