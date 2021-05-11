package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseMovie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<ResponseMovie>>

    @Before
    fun init() {
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val movieDataDummy = DataDummy.getPopularMovieDummy()
        val moviesDataLiveMock = MutableLiveData<List<ResponseMovie>>()
        moviesDataLiveMock.value = movieDataDummy

        `when`(moviesRepository.getMoviesData()).thenReturn(moviesDataLiveMock)
        val moviesData = viewModel.getMovies().value
        verify(moviesRepository).getMoviesData()
        assertNotNull(moviesData)
        assertEquals(moviesData?.size, movieDataDummy.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieDataDummy)
    }

}