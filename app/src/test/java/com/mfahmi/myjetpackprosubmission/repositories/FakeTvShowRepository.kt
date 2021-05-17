package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailTvShowCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetTvShowsCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseTvShow

class FakeTvShowRepository(private val remoteDataSource: RemoteDataSource) {

    fun getTvShowsData(): LiveData<List<ResponseTvShow>> {
        val tvShowsData = MutableLiveData<List<ResponseTvShow>>()
        remoteDataSource.getPopularTvShows(object : GetTvShowsCallback {
            override fun onResponse(responseTvShows: List<ResponseTvShow>) {
                tvShowsData.postValue(responseTvShows)
            }
        })
        return tvShowsData
    }

    fun getTvShowDetailData(tvShowId: Int): LiveData<ResponseDetailTvShow> {
        val tvShowDetailData = MutableLiveData<ResponseDetailTvShow>()
        remoteDataSource.getDetailTvShow(tvShowId, object : GetDetailTvShowCallback {
            override fun onResponse(responseDetailTvShow: ResponseDetailTvShow) {
                tvShowDetailData.postValue(responseDetailTvShow)
            }
        })
        return tvShowDetailData
    }
}