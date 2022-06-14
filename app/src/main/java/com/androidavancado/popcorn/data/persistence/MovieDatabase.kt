package com.androidavancado.popcorn.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidavancado.popcorn.data.persistence.dao.MovieDao
import com.androidavancado.popcorn.data.persistence.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

}