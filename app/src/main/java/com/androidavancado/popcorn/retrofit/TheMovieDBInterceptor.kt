package com.androidavancado.popcorn.retrofit

import com.androidavancado.popcorn.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

//Esta clase intercepta lo que mandemos hacia afuera

class TheMovieDBInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
       //Añadimos parametros como la api key y el lenguaje a la URL de la cadena que recibimos (chain) (interceptamos)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_API_LANGUAGE, "es-ES")
            .build()

        var request = chain.request()

        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept","application/json")
            .build()

        return chain.proceed(request)
    }
}