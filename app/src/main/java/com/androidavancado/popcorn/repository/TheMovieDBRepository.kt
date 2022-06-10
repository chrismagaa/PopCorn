package com.androidavancado.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.androidavancado.popcorn.common.MyApp
import com.androidavancado.popcorn.retrofit.TheMovieDBClient
import com.androidavancado.popcorn.retrofit.TheMovieDBService
import com.androidavancado.popcorn.retrofit.models.Movie
import com.androidavancado.popcorn.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMOvieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMOvieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMOvieDBClient?.getTheMovieDBService()

        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>? {
            if(popularMovies == null){
                popularMovies = MutableLiveData<List<Movie>>()
            }

        val call: Call<PopularMoviesResponse>? = theMovieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMoviesResponse> {
            override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
               if(response.isSuccessful){
                   popularMovies?.value = response.body()?.results
               }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }

        })

        return popularMovies
    }
}