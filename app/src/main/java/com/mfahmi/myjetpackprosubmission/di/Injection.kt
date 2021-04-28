package com.mfahmi.myjetpackprosubmission.di

import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository

object Injection {
    fun movieInjectRepository(): MoviesRepository {
        return MoviesRepository.getInstance
    }

    fun tvShowInjectRepository(): TvShowRepository {
        return TvShowRepository.getInstance
    }
}