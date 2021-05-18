package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

interface MovieDataSourceContract {
    fun getMoviesData(): LiveData<ResourceValue<PagedList<MovieEntity>>>

    fun checkBookmarkMovie(movieId: Int): LiveData<Boolean>

    fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean)

    fun getBookmarkDataMovie(): LiveData<PagedList<MovieEntity>>
}