package com.androidavancado.popcorn.domain.model

import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.data.persistence.entities.MovieEntity

data class Movie (
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>?,
        val id: Int,
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

fun MovieModel.toDomain() = Movie(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count)

fun MovieEntity.toDomain() = Movie(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count)