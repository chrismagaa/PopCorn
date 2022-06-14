package com.androidavancado.popcorn.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.androidavancado.popcorn.data.model.MovieModel
import com.androidavancado.popcorn.databinding.FragmentMovieListBinding
import com.androidavancado.popcorn.domain.model.Movie
import com.androidavancado.popcorn.ui.movies.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private  val moviesViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private  var popularMovies: List<Movie> = ArrayList()

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)


        movieAdapter = MovieAdapter{movie ->
            Toast.makeText(requireContext(), movie.overview, Toast.LENGTH_SHORT).show()
        }

        binding.list.adapter = movieAdapter
        binding.list.layoutManager = GridLayoutManager(requireContext(), 2)

        moviesViewModel.onCreate()
        moviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })

        moviesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}