package com.androidavancado.popcorn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.androidavancado.popcorn.common.Constantes
import com.androidavancado.popcorn.databinding.ActivityDetailBinding
import com.androidavancado.popcorn.domain.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }


    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        movie?.let {
            binding.tvOverview.text = it.overview
            binding.tvMovieTitle.text = it.title
            binding.ivMovie.load(Constantes.IMAGE_BASE_URL + it.poster_path){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }

        setContentView(binding.root)
    }
}