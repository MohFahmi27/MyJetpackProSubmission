package com.mfahmi.myjetpackprosubmission.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.viewmodels.DetailViewModel
import com.mfahmi.myjetpackprosubmission.viewmodels.MovieViewModel
import com.mfahmi.myjetpackprosubmission.viewmodels.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryMovie(private val moviesRepository: MoviesRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MovieViewModel(moviesRepository) as T
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryTvShows(private val tvShowRepository: TvShowRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            TvShowViewModel(tvShowRepository) as T
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryDetail(private val moviesRepository: MoviesRepository,
                             private val tvShowRepository: TvShowRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            DetailViewModel(moviesRepository, tvShowRepository) as T
}