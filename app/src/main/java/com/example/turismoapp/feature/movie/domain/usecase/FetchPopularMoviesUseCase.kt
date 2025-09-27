package com.example.turismoapp.feature.movie.domain.usecase

import com.example.turismoapp.feature.movie.domain.model.MovieModel
import com.example.turismoapp.feature.movie.domain.repository.IMoviesRepository

class FetchPopularMoviesUseCase(
    private val movieRepository: IMoviesRepository
) {
    suspend fun invoke(): Result<List<MovieModel>> {
        val result = movieRepository.fetchPopularMovies()
        return result.map { movies ->
            val likedMovies = mutableListOf<MovieModel>()
            val unlikedMovies = mutableListOf<MovieModel>()

            movies.forEach { movie ->
                if (movieRepository.isMovieLiked(movie.title)) {
                    likedMovies.add(movie)
                } else {
                    unlikedMovies.add(movie)
                }
            }
            likedMovies + unlikedMovies
        }
    }
}