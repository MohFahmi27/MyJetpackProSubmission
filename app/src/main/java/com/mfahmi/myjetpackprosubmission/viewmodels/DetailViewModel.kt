package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

class DetailViewModel(
        private val moviesRepository: MoviesRepository,
        private val tvShowRepository: TvShowRepository,
) : ViewModel() {
    var id: Int = 0

    fun getMovieDetail(): LiveData<ResponseDetailMovie> {
        return moviesRepository.getDetailMoviesData(id)
    }

    fun getTvShowDetail(): LiveData<ResponseDetailTvShow> {
        return tvShowRepository.getTvShowDetailData(id)
    }

}