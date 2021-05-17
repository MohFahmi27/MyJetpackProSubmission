package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    internal fun getMovies(): LiveData<ResourceValue<PagedList<MovieEntity>>> =
        moviesRepository.getMoviesData()
}