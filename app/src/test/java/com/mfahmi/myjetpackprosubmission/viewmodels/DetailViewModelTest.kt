package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseDetailTvShow
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var thrown = ExpectedException.none()

    private var moviesRepository = mock(MoviesRepository::class.java)

    private var tvShowRepository = mock(TvShowRepository::class.java)

    private var observer = mock(Observer::class.java)

    private val idTestMovie = 399566
    private val idTestTvShow = 791373

    @Before
    fun init() {
        viewModel = DetailViewModel(moviesRepository, tvShowRepository)
    }

    @Test
    fun validateDataMovieWithId() {
        val movieDataDummy = DataDummy.getDetailMovie(idTestMovie)
        val moviesDataLiveMock = MutableLiveData<ResponseDetailMovie>()
        moviesDataLiveMock.value = movieDataDummy

        `when`(moviesRepository.getDetailMoviesData(idTestMovie)).thenReturn(moviesDataLiveMock)
        viewModel.id = idTestMovie
        assertNotNull(viewModel.getMovieDetail())
        verify(moviesRepository).getDetailMoviesData(viewModel.id)
        assertEquals(moviesDataLiveMock, viewModel.getMovieDetail())

        viewModel.getMovieDetail().observeForever(observer as Observer<ResponseDetailMovie>)
        verify(observer as Observer<ResponseDetailMovie>).onChanged(movieDataDummy)
    }

    @Test
    fun validateDataTvShowWithId() {
        val tvShowDataDummy = DataDummy.getDetailTvShow(idTestTvShow)
        val tvShowDataLiveMock = MutableLiveData<ResponseDetailTvShow>()
        tvShowDataLiveMock.value = tvShowDataDummy

        `when`(tvShowRepository.getTvShowDetailData(idTestTvShow)).thenReturn(tvShowDataLiveMock)
        viewModel.id = idTestTvShow
        assertNotNull(viewModel.getTvShowDetail())
        verify(tvShowRepository).getTvShowDetailData(viewModel.id)
        assertEquals(tvShowDataLiveMock, viewModel.getTvShowDetail())

        viewModel.getTvShowDetail().observeForever(observer as Observer<ResponseDetailTvShow>)
        verify(observer as Observer<ResponseDetailTvShow>).onChanged(tvShowDataDummy)
    }

    @Test
    @Throws(NullPointerException::class)
    fun invalidIdMovie() {
        thrown.expect(NullPointerException::class.java)
        val movieDataDummy = DataDummy.getDetailMovie(0)
        val moviesDataLiveMock = MutableLiveData<ResponseDetailMovie>()
        moviesDataLiveMock.value = movieDataDummy

        thrown.expect(NullPointerException::class.java)
        `when`(moviesRepository.getDetailMoviesData(0)).thenReturn(moviesDataLiveMock)
        viewModel.id = 0
        assertNull(viewModel.getMovieDetail())
    }

    @Test
    @Throws(NullPointerException::class)
    fun invalidIdTvShow() {
        thrown.expect(NullPointerException::class.java)
        val tvShowDataDummy = DataDummy.getDetailTvShow(0)
        val tvShowDataLiveMock = MutableLiveData<ResponseDetailTvShow>()
        tvShowDataLiveMock.value = tvShowDataDummy

        thrown.expect(NullPointerException::class.java)
        `when`(tvShowRepository.getTvShowDetailData(0)).thenReturn(tvShowDataLiveMock)
        viewModel.id = 0
        assertNull(viewModel.getTvShowDetail())
    }

}