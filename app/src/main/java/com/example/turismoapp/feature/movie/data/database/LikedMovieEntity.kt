package com.example.turismoapp.feature.movie.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_movies")
data class LikedMovieEntity(
    @PrimaryKey
    val title: String,
    val pathUrl: String
)