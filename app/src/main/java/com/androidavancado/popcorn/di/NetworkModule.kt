package com.androidavancado.popcorn.di

import android.util.Log
import com.androidavancado.popcorn.common.Constantes
import com.androidavancado.popcorn.data.network.TheMovieDBClient
import com.androidavancado.popcorn.data.network.TheMovieDBInterceptor
import com.androidavancado.popcorn.data.network.TheMovieDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor(TheMovieDBInterceptor())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constantes.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TheMovieDBService {
        return retrofit.create(TheMovieDBService::class.java)
    }

}