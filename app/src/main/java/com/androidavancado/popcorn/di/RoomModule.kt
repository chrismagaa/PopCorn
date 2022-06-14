package com.androidavancado.popcorn.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidavancado.popcorn.data.persistence.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val MOVIE_DATABASE_NAME = "movie_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, MOVIE_DATABASE_NAME).build()


    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()

}

