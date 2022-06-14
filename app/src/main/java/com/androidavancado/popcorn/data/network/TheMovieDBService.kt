package com.androidavancado.popcorn.data.network

import com.androidavancado.popcorn.data.model.PopularMoviesResponse
import retrofit2.Response

import retrofit2.http.GET

interface TheMovieDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMoviesResponse>

}