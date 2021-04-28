package com.mfahmi.myjetpackprosubmission.viewmodels

import com.mfahmi.myjetpackprosubmission.models.TvShowEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowsList: ArrayList<TvShowEntity>

    @Before
    fun init() {
        viewModel = TvShowViewModel()
        tvShowsList = viewModel.getTvShows()
    }

    @Test
    fun validateSizeInViewModelFromRepository() {
        assertNotNull(tvShowsList)
        assertEquals(10, tvShowsList.size)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
//        assertEquals(tvShowsList[1], TvShowRepository.getTvShowData()[1])
//        assertFalse(tvShowsList == MoviesRepository.getMoviesData())
    }

}