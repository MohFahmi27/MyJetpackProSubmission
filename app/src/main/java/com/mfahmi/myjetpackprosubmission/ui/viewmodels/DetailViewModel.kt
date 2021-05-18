package com.mfahmi.myjetpackprosubmission.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

class DetailViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {
    var id: Int = 0

    fun checkBookmarkMovie(movieId: Int): LiveData<Boolean> = moviesRepository.checkBookmarkMovie(movieId)

    fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean) {
        moviesRepository.setBookmarkMovie(movieEntity, status)
    }

    fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean> =
        tvShowRepository.checkBookmarkTvShow(tvShowId)

    fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean) {
        tvShowRepository.setBookmarkTvShow(tvShowEntity, status)
    }

}