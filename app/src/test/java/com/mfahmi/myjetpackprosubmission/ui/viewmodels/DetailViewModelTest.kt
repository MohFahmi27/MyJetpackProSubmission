package com.mfahmi.myjetpackprosubmission.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var thrown = ExpectedException.none()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var stateObserver: Observer<Boolean>

    private val idTestMovie = 399566
    private val idTestTvShow = 791373

    @Before
    fun init() {
        viewModel = DetailViewModel(moviesRepository, tvShowRepository)
    }

    @Test
    fun checkBookmarkMovie() {
        val movieDataById = DataDummy.getPopularMovieDummy().find { it.movieId == idTestMovie }
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = movieDataById?.isBookmark

        `when`(moviesRepository.checkBookmarkMovie(idTestMovie)).thenReturn(stateOnDB)
        viewModel.checkBookmarkMovie(idTestMovie).observeForever(stateObserver)
        verify(stateObserver).onChanged(movieDataById?.isBookmark)
    }

    @Test
    fun checkBookmarkTvShow() {
        val tvShowDataById = DataDummy.getPopularTvShowDummy().find { it.tvShowId == idTestTvShow }
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = tvShowDataById?.isBookmark

        `when`(tvShowRepository.checkBookmarkTvShow(idTestTvShow)).thenReturn(stateOnDB)
        viewModel.checkBookmarkTvShow(idTestTvShow).observeForever(stateObserver)
        verify(stateObserver).onChanged(tvShowDataById?.isBookmark)
    }
}