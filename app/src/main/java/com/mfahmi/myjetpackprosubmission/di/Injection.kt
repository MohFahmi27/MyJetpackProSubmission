package com.mfahmi.myjetpackprosubmission.di

import android.content.Context
import com.mfahmi.myjetpackprosubmission.api.ApiConfig
import com.mfahmi.myjetpackprosubmission.data.local.LocalDataSource
import com.mfahmi.myjetpackprosubmission.data.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.db.BookmarkRoomDatabase
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import com.mfahmi.myjetpackprosubmission.utils.AppExecutors

object Injection {
    fun movieInjectRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig)
        val localDataSource =
            LocalDataSource.getInstance(BookmarkRoomDatabase.getInstance(context).bookmarkDao())
        return MoviesRepository.getInstance(remoteDataSource, localDataSource, AppExecutors())
    }

    fun tvShowInjectRepository(context: Context): TvShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig)
        val localDataSource =
            LocalDataSource.getInstance(BookmarkRoomDatabase.getInstance(context).bookmarkDao())
        return TvShowRepository.getInstance(remoteDataSource, localDataSource, AppExecutors())
    }
}