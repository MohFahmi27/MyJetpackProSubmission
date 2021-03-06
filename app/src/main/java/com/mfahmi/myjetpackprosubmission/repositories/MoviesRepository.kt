package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfahmi.myjetpackprosubmission.data.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.remote.ApiResponse
import com.mfahmi.myjetpackprosubmission.data.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.data.remote.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.utils.AppExecutors
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

class MoviesRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSourceContract {

    companion object {
        @Volatile
        private var INSTANCE: MoviesRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MoviesRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MoviesRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    private val config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
    }

    override fun getMoviesData(): LiveData<ResourceValue<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ResponseItemMovies>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(localDataSource.getMovieFromDb(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseItemMovies>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ResponseItemMovies) {
                localDataSource.insertMovieToDB(data.results)
            }

        }.asLiveData()
    }

    override fun checkBookmarkMovie(movieId: Int): LiveData<Boolean> =
        localDataSource.checkMovieById(movieId)

    override fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean) =
        appExecutors.diskIO().execute { localDataSource.updateMovieFromDb(movieEntity, status) }

    override fun getBookmarkDataMovie(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(localDataSource.getBookmarkMovieData(), config).build()
    }

}