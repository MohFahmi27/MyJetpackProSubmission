package com.mfahmi.myjetpackprosubmission.repositories.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.mfahmi.myjetpackprosubmission.db.BookmarkDao
import com.mfahmi.myjetpackprosubmission.db.BookmarkRoomDatabase
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(application: Application) {

//    companion object : SingletonHolder<LocalDataSource, BookmarkDao>(::LocalDataSource)
    private val myBookmarkDao: BookmarkDao

    init {
        val db = BookmarkRoomDatabase.getInstance(application)
        myBookmarkDao = db.bookmarkDao()
    }

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getMovieFromDb(): LiveData<List<MovieEntity>> = myBookmarkDao.getBookmarkedMovie()

    fun getTvShowFromDb(): LiveData<List<TvShowEntity>> = myBookmarkDao.getBookmarkedTvShow()

    fun insertMovieBookmark(movieEntity: MovieEntity) =
        executorService.execute { myBookmarkDao.insertMovieBookmark(movieEntity) }

    fun insertTvShowBookmark(tvShowEntity: TvShowEntity) =
        myBookmarkDao.insertTvShowBookmark(tvShowEntity)

    fun deleteMovieFromDb(movieEntity: MovieEntity) =
        myBookmarkDao.deleteMovieBookmarked(movieEntity)

    fun deleteTvShowFromDb(tvShowEntity: TvShowEntity) =
        myBookmarkDao.deleteTvShowBookmarked(tvShowEntity)

}