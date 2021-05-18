package com.mfahmi.myjetpackprosubmission.data.local

import androidx.paging.DataSource
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity
import com.mfahmi.myjetpackprosubmission.db.BookmarkDao
import com.mfahmi.myjetpackprosubmission.utils.SingletonHolder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val myBookmarkDao: BookmarkDao) {

    companion object : SingletonHolder<LocalDataSource, BookmarkDao>(::LocalDataSource)

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getMovieFromDb(): DataSource.Factory<Int, MovieEntity> = myBookmarkDao.getMovie()

    fun getTvShowFromDb(): DataSource.Factory<Int, TvShowEntity> = myBookmarkDao.getTvShow()

    fun insertMovieToDB(movies: List<MovieEntity>) =
        executorService.execute { myBookmarkDao.insertMovies(movies) }

    fun insertTvShowToDB(tvShows: List<TvShowEntity>) =
        executorService.execute { myBookmarkDao.insertTvShow(tvShows) }

    fun updateMovieFromDb(movieEntity: MovieEntity, status: Boolean) {
        movieEntity.isBookmark = status
        myBookmarkDao.updateBookmarkMovie(movieEntity)
    }

    fun updateTvShowFromDb(tvShowEntity: TvShowEntity, status: Boolean) {
        tvShowEntity.isBookmark = status
        myBookmarkDao.updateBookmarkTvShow(tvShowEntity)
    }

    fun checkMovieById(movieId: Int) = myBookmarkDao.checkMovieBookmark(movieId)

    fun checkTvShowById(tvShowId: Int) = myBookmarkDao.checkTvShowBookmark(tvShowId)

    fun getBookmarkMovieData() = myBookmarkDao.getBookmarkedMovies()

    fun getBookmarkTvShowData() = myBookmarkDao.getBookmarkedTvShow()

}