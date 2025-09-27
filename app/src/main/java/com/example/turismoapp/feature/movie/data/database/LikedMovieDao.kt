package com.example.turismoapp.feature.movie.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LikedMovieDao {
    @Query("SELECT * FROM liked_movies")
    suspend fun getAllLikedMovies(): List<LikedMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeMovie(movie: LikedMovieEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM liked_movies WHERE title = :title)")
    suspend fun isMovieLiked(title: String): Boolean

    @Query("DELETE FROM liked_movies WHERE title = :title")
    suspend fun unlikeMovie(title: String)
}