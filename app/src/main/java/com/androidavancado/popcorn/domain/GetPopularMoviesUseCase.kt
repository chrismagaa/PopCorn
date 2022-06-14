package com.androidavancado.popcorn.domain

import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.data.persistence.entities.toLocalDB
import com.androidavancado.popcorn.domain.model.Movie
import com.androidavancado.popcorn.repository.TheMovieDBRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: TheMovieDBRepository){

    suspend operator fun invoke(): List<Movie>{
        val movies = repository.getPopularMoviesFromApi()

        return if(movies.isNotEmpty()){
            repository.clearMovies()
            repository.insertMovies(movies.map { it.toLocalDB() })
            movies
        }else{
            return repository.getPopularMoviesFromLocalDB()
        }
    }

}