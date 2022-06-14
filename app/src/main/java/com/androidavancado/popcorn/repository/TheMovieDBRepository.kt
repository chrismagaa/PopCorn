package com.androidavancado.popcorn.repository

import com.androidavancado.popcorn.data.persistence.dao.MovieDao
import com.androidavancado.popcorn.data.network.TheMovieDBClient
import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.data.persistence.entities.MovieEntity
import com.androidavancado.popcorn.domain.model.Movie
import com.androidavancado.popcorn.domain.model.toDomain
import javax.inject.Inject

class TheMovieDBRepository @Inject constructor(
    private val  apiClient: TheMovieDBClient,
    private val movieDao: MovieDao
) {
    suspend fun getPopularMoviesFromApi(): List<Movie> {
        val response  = apiClient.getPopularMovies()
        response.toString()
        return response.map { it.toDomain() }
    }

    suspend fun getPopularMoviesFromLocalDB(): List<Movie>{
        val response = movieDao.getAllMovies()
        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertAll(movies)
    }

    suspend fun clearMovies() {
        movieDao.deleteAllMovies()
    }
}