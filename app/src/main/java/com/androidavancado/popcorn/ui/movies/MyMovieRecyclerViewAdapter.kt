package com.androidavancado.popcorn.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.androidavancado.popcorn.R
import com.androidavancado.popcorn.common.Constantes
import com.androidavancado.popcorn.retrofit.models.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.view.*


class MyMovieRecyclerViewAdapter() : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()
        holder.ivPhoto.load(Constantes.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            transformations(CircleCropTransformation())
        }


    }

    override fun getItemCount(): Int = movies.size

    fun setData(popularMovies: List<Movie>?) {

        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.imageViewPhoto
        val tvTitle: TextView = view.textViewTitle
        val tvRating: TextView = view.textViewRating

    }
}