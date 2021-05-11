package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mfahmi.myjetpackprosubmission.data.DataDummy
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseTvShow
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<List<ResponseTvShow>>

    @Before
    fun init() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val tvShowsDataDummy = DataDummy.getPopularTvShowDummy()
        val tvShowsMock = MutableLiveData<List<ResponseTvShow>>()
        tvShowsMock.value = tvShowsDataDummy

        `when`(tvShowRepository.getTvShowsData()).thenReturn(tvShowsMock)
        val tvShowData = viewModel.getTvShows().value
        verify(tvShowRepository).getTvShowsData()
        assertNotNull(tvShowData)
        assertEquals(tvShowData?.size, tvShowsDataDummy.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowsDataDummy)
    }

}