package com.androidavancado.popcorn.ui.movies.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.androidavancado.popcorn.R
import com.androidavancado.popcorn.domain.model.Movie


class MovieAdapter(
    private val onClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }


}