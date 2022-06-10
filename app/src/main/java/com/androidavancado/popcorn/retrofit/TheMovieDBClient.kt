package com.androidavancado.popcorn.retrofit

import com.androidavancado.popcorn.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMoviewDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object{
        var instance: TheMovieDBClient? = null
        get(){
            if(field == null){
                instance = TheMovieDBClient()
            }
            return field
        }
    }

    init {
        // Incluir el interceptor que hemos definido para las peticiones

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val cliente = okHttpClientBuilder.build()

        //Construir el cliente de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        //Instanciamos el servicio de Retrofit a partir del objeto Retrofit
        theMoviewDBService = retrofit.create(TheMovieDBService::class.java)

    }

    fun getTheMovieDBService() = theMoviewDBService
}