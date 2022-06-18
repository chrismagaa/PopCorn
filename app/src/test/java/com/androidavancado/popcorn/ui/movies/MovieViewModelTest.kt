package com.androidavancado.popcorn.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androidavancado.popcorn.domain.GetPopularMoviesUseCase
import com.androidavancado.popcorn.domain.model.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest{

    @RelaxedMockK
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        movieViewModel = MovieViewModel(getPopularMoviesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get popular movies`() = runTest {
        //Given
        val movieList = listOf(Movie(true,"", listOf(), 2,"","","",3.4,"","","",false,3.4,19))
        coEvery { getPopularMoviesUseCase() } returns movieList

        //When
        movieViewModel.onCreate()

        //Then
        assert(movieViewModel.popularMovies.value == movieList)

    }

}