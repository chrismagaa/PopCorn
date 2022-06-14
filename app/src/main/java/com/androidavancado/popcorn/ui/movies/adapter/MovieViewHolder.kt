package com.androidavancado.popcorn.ui.movies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.androidavancado.popcorn.common.Constantes
import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.databinding.ItemMovieBinding
import com.androidavancado.popcorn.domain.model.Movie

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemMovieBinding.bind(view)

    fun render(movie: Movie, onClick: (Movie) -> Unit){
        binding.itemMovie.setOnClickListener { onClick(movie) }
        binding.textViewTitle.text = movie.title
        binding.textViewRating.text = movie.vote_average.toString()
        binding.imageViewPhoto.load(Constantes.IMAGE_BASE_URL + movie.poster_path){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }
}