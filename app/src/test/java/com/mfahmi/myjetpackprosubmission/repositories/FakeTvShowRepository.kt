package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.data.remote.ApiResponse
import com.mfahmi.myjetpackprosubmission.data.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.data.remote.models.tvshow.ResponseItemTvShows
import com.mfahmi.myjetpackprosubmission.utils.AppExecutors
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

class FakeTvShowRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : TvShowDataSourceContract {

    private val config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
    }

    override fun getTvShowsData(): LiveData<ResourceValue<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ResponseItemTvShows>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(localDataSource.getTvShowFromDb(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseItemTvShows>> {
                return remoteDataSource.getPopularTvShows()
            }

            override fun saveCallResult(data: ResponseItemTvShows) {
                localDataSource.insertTvShowToDB(data.results)
            }

        }.asLiveData()
    }

    override fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean> =
        localDataSource.checkTvShowById(tvShowId)

    override fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean) =
        appExecutors.diskIO().execute { localDataSource.updateTvShowFromDb(tvShowEntity, status) }

    override fun getBookmarkDataTvShow(): LiveData<PagedList<TvShowEntity>> {
        return LivePagedListBuilder(localDataSource.getBookmarkTvShowData(), config).build()
    }
}