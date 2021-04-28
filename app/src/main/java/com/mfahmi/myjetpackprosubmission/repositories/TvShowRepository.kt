package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseTvShow
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailTvShowCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetTvShowsCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteRepositories

class TvShowRepository private constructor(private val remoteRepositories: RemoteRepositories){
    companion object {
        val getInstance by lazy { TvShowRepository(RemoteRepositories.getInstance) }
    }

    fun getTvShowsData(): LiveData<List<ResponseTvShow>> {
        val tvShowsData = MutableLiveData<List<ResponseTvShow>>()
        remoteRepositories.getPopularTvShows(object : GetTvShowsCallback {
            override fun onResponse(responseTvShows: List<ResponseTvShow>) {
                tvShowsData.postValue(responseTvShows)
            }
        })
        return tvShowsData
    }

    fun getTvShowDetailData(tvShowId: Int): LiveData<ResponseDetailTvShow> {
        val tvShowDetailData = MutableLiveData<ResponseDetailTvShow>()
        remoteRepositories.getDetailTvShow(tvShowId, object : GetDetailTvShowCallback {
            override fun onResponse(responseDetailTvShow: ResponseDetailTvShow) {
                tvShowDetailData.postValue(responseDetailTvShow)
            }
        })
        return tvShowDetailData
    }

}