package com.androidavancado.popcorn.data.network

import android.util.Log
import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.data.model.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import javax.inject.Inject

class TheMovieDBClient @Inject constructor(private val api: TheMovieDBService) {

    suspend fun getPopularMovies(): List<MovieModel> {
           return try{
                val response = api.getPopularMovies()
                response.body()?.results ?: emptyList()
            }catch (e: TheMovieDBInterceptor.NoInternetException){
               Log.d(TheMovieDBClient::class.java.simpleName, e.message, e)
               emptyList()
            }
    }

}