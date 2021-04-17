package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.MoviesEntity
import com.mfahmi.myjetpackprosubmission.models.TvShowEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

class DetailViewModel : ViewModel() {
    var id: Int = 0

    fun getMovieDetail(): MoviesEntity? {
        val list = MoviesRepository.getMoviesData()
        return list.find { it.movieId == id }
    }

    fun getTvShowDetail(): TvShowEntity? {
        val list = TvShowRepository.getTvShowData()
        return list.find { it.tvShowId == id }
    }

}