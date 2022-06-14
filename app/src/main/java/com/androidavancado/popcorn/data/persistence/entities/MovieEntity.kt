package com.androidavancado.popcorn.data.persistence.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.androidavancado.popcorn.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity @JvmOverloads constructor(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    @Ignore val genre_ids: List<Int>? = null,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun Movie.toLocalDB() = MovieEntity(
    id,
    adult,
    backdrop_path,
    genre_ids,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)