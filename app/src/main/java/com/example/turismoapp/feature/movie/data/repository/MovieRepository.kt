package com.example.turismoapp.feature.movie.data.repository

import com.example.turismoapp.feature.movie.data.datasource.MovieRemoteDataSource
import com.example.turismoapp.feature.movie.data.database.LikedMovieDao
import com.example.turismoapp.feature.movie.data.database.LikedMovieEntity
import com.example.turismoapp.feature.movie.domain.model.MovieModel
import com.example.turismoapp.feature.movie.domain.repository.IMoviesRepository

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val likedMovieDao: LikedMovieDao
): IMoviesRepository {
    override suspend fun fetchPopularMovies(): Result<List<MovieModel>>
            = movieRemoteDataSource.fetchPopularMovies()

    override suspend fun likeMovie(movie: MovieModel) {
        likedMovieDao.likeMovie(LikedMovieEntity(movie.title, movie.pathUrl))
    }

    override suspend fun unlikeMovie(movie: MovieModel) {
        likedMovieDao.unlikeMovie(movie.title)
    }

    override suspend fun isMovieLiked(title: String): Boolean {
        return likedMovieDao.isMovieLiked(title)
    }
}