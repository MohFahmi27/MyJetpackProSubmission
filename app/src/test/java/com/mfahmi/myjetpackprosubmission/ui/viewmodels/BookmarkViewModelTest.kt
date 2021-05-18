package com.mfahmi.myjetpackprosubmission.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Before
    fun init() {
        viewModel = BookmarkViewModel(moviesRepository, tvShowRepository)
    }

    @Test
    fun getBookmarkMovie() {
        val movieDataDummy = pagedListMovie
        `when`(movieDataDummy.size).thenReturn(10)
        val moviesDataLiveMock = MutableLiveData<PagedList<MovieEntity>>()
        moviesDataLiveMock.value = movieDataDummy

        `when`(moviesRepository.getBookmarkDataMovie()).thenReturn(moviesDataLiveMock)
        val movieData = viewModel.getBookmarkMovie().value
        verify(moviesRepository).getBookmarkDataMovie()
        assertNotNull(movieData)
        assertEquals(10, movieData?.size)

        viewModel.getBookmarkMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDataDummy)
    }

    @Test
    fun getBookmarkTvShow() {
        val tvShowDataDummy = pagedListTvShow
        `when`(tvShowDataDummy.size).thenReturn(10)
        val tvShowDataLiveMock = MutableLiveData<PagedList<TvShowEntity>>()
        tvShowDataLiveMock.value = tvShowDataDummy

        `when`(tvShowRepository.getBookmarkDataTvShow()).thenReturn(tvShowDataLiveMock)
        val tvShowData = viewModel.getBookmarkTvShow().value
        verify(tvShowRepository).getBookmarkDataTvShow()
        assertNotNull(tvShowData)
        assertEquals(10, tvShowData?.size)

        viewModel.getBookmarkTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowDataDummy)
    }
}