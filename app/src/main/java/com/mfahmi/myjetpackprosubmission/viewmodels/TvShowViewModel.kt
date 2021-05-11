package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseTvShow

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {
    internal fun getTvShows(): LiveData<List<ResponseTvShow>> = tvShowRepository.getTvShowsData()
}