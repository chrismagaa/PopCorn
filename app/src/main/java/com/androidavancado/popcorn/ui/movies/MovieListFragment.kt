package com.androidavancado.popcorn.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidavancado.popcorn.R
import com.androidavancado.popcorn.retrofit.models.Movie


class MovieListFragment : Fragment() {

    private var columnCount = 2
    private lateinit var moviesViewModel: MovieViewModel
    private lateinit var movieAdapter: MyMovieRecyclerViewAdapter
    private  var popularMovies: List<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list_list, container, false)

        // Obtenemos el ViewModel
        moviesViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        movieAdapter = MyMovieRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }

        // Observer de las peliculas
        moviesViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}