package com.androidavancado.popcorn.data.model

data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)