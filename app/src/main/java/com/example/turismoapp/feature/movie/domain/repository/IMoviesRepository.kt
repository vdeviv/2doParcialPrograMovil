package com.example.turismoapp.feature.movie.domain.repository

import com.example.turismoapp.feature.movie.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun fetchPopularMovies(): Result<List<MovieModel>>
    suspend fun likeMovie(movie: MovieModel)
    suspend fun unlikeMovie(movie: MovieModel)
    suspend fun isMovieLiked(title: String): Boolean
}