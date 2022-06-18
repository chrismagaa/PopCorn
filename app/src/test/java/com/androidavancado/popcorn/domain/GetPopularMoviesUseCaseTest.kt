package com.androidavancado.popcorn.domain

import com.androidavancado.popcorn.data.persistence.entities.toLocalDB
import com.androidavancado.popcorn.domain.model.Movie
import com.androidavancado.popcorn.repository.TheMovieDBRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.stub.StubRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetPopularMoviesUseCaseTest {

    @RelaxedMockK
    private lateinit var movieRepository: TheMovieDBRepository

    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery {
            movieRepository.getPopularMoviesFromApi()
        } returns emptyList()

        //When
        getPopularMoviesUseCase()

        //Then
        coVerify(exactly = 1) { movieRepository.getPopularMoviesFromLocalDB() }
    }


    @Test
    fun `when the api return something then get values from api`() = runBlocking{
        //Given
        val myList = listOf(Movie(true,"", listOf(), 2,"","","",3.4,"","","",false,3.4,19))
        coEvery {
            movieRepository.getPopularMoviesFromApi()
        }returns myList

        //When
        val response = getPopularMoviesUseCase()

        //Then
        coVerify(exactly = 1) { movieRepository.clearMovies() }
        coVerify(exactly = 1) { movieRepository.insertMovies(any()) }
        coVerify(exactly = 0) { movieRepository.getPopularMoviesFromLocalDB() }
        assert(myList == response)

    }




}