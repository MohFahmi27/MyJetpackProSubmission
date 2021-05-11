package com.mfahmi.myjetpackprosubmission.di

import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteRepositories

object Injection {
    fun movieInjectRepository(): MoviesRepository {
        return MoviesRepository.getInstance(RemoteRepositories.getInstance)
    }

    fun tvShowInjectRepository(): TvShowRepository {
        return TvShowRepository.getInstance(RemoteRepositories.getInstance)
    }
}