package com.mfahmi.myjetpackprosubmission.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseDetailTvShow

class DetailViewModel(
    application: Application,
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

    private val localDataSource = LocalDataSource(application)
    fun insertBookmark(movieEntity: MovieEntity) {
        localDataSource.insertMovieBookmark(movieEntity)
    }

}