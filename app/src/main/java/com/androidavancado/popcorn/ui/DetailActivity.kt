package com.androidavancado.popcorn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import coil.load
import coil.transform.CircleCropTransformation
import com.androidavancado.popcorn.R
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

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        movie?.let {
            binding.tvRanking.text = it.vote_average.toString()
            binding.tvOverview.text = it.overview
            binding.tvMovieTitle.text = it.title
            binding.ivMovie.load(Constantes.IMAGE_BASE_URL + it.poster_path){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }



        setContentView(binding.root)
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}