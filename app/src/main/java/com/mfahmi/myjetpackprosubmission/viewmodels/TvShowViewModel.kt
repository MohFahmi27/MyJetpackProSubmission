package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {
    internal fun getTvShows(): LiveData<ResourceValue<PagedList<TvShowEntity>>> =
        tvShowRepository.getTvShowsData()
}