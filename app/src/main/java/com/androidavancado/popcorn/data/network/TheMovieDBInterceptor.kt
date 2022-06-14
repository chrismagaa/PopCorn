package com.androidavancado.popcorn.data.network

import com.androidavancado.popcorn.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

//Esta clase intercepta lo que mandemos hacia afuera

class TheMovieDBInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isInternetAvailable()) {
            throw NoInternetException()
        } else {

            val original = chain.request()
            val originalHttpUrl = original.url()


            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
                .addQueryParameter(Constantes.URL_PARAM_API_LANGUAGE, "es-ES")
                .build()


            // Request customization: add request headers
            val requestBuilder = original.newBuilder().url(url)


            val request = requestBuilder
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()

            chain.proceed(request)
        }

    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockaddr = InetSocketAddress("8.8.8.8", 53)

            sock.connect(sockaddr, timeoutMs)
            sock.close()

            true
        } catch (e: IOException) {
            false
        }
    }

    class NoInternetException() : IOException() {
        override val message: String
            get() =
                "No internet available, please check your connected WIFi or Data"
    }
}