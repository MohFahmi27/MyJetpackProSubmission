package com.mfahmi.myjetpackprosubmission.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

class BookmarkViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    fun getBookmarkMovie(): LiveData<PagedList<MovieEntity>> = moviesRepository.getBookmarkDataMovie()

    fun getBookmarkTvShow(): LiveData<PagedList<TvShowEntity>> = tvShowRepository.getBookmarkDataTvShow()

}