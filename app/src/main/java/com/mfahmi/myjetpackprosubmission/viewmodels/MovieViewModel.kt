package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository

class MovieViewModel : ViewModel() {
    internal fun getMovies(): ArrayList<MoviesEntity> = MoviesRepository.getMoviesData()
}