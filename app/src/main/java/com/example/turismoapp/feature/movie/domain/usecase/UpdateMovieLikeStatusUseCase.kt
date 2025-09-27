package com.example.turismoapp.feature.movie.domain.usecase

import com.example.turismoapp.feature.movie.domain.model.MovieModel
import com.example.turismoapp.feature.movie.domain.repository.IMoviesRepository

class UpdateMovieLikeStatusUseCase(
    private val movieRepository: IMoviesRepository
) {
    suspend fun invoke(movie: MovieModel, isLiked: Boolean) {
        if (isLiked) {
            movieRepository.likeMovie(movie)
        } else {
            movieRepository.unlikeMovie(movie)
        }
    }
}