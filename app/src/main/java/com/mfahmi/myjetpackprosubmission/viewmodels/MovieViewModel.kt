package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseMovie
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    internal fun getMovies(): LiveData<List<ResponseMovie>> = moviesRepository.getMoviesData()
}