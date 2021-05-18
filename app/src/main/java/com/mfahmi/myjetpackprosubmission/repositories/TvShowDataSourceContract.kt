package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

interface TvShowDataSourceContract {
    fun getTvShowsData(): LiveData<ResourceValue<PagedList<TvShowEntity>>>

    fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean>

    fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean)

    fun getBookmarkDataTvShow(): LiveData<PagedList<TvShowEntity>>
}