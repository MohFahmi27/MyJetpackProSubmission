package com.mfahmi.myjetpackprosubmission.viewmodels

import androidx.lifecycle.ViewModel
import com.mfahmi.myjetpackprosubmission.models.TvShowEntity
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

class TvShowViewModel : ViewModel() {
    internal fun getTvShows(): ArrayList<TvShowEntity> = TvShowRepository.getTvShowData()
}