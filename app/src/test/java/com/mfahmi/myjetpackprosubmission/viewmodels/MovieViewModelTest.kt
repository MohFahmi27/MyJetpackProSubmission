package com.mfahmi.myjetpackprosubmission.viewmodels

import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var moviesList: ArrayList<MoviesEntity>

    @Before
    fun init() {
        viewModel = MovieViewModel()
        moviesList = viewModel.getMovies()
    }

    @Test
    fun validateSizeInViewModelFromRepository() {
        assertNotNull(moviesList)
        assertEquals(10, moviesList.size)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
//        assertEquals(moviesList, MoviesRepository.getMoviesData())
//        assertFalse(moviesList == TvShowRepository.getTvShowData())
    }

}