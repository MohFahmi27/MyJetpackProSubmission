package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.models.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository

class MovieViewModel : ViewModel() {
    internal fun getMovies(): ArrayList<MoviesEntity> = MoviesRepository.getMoviesData()
    internal fun getMoviesFromApi(): LiveData<ResponseItemMovies> = MoviesRepository.getMovies()
    internal fun cancelJob() = MoviesRepository.cancelJob()
}