package com.mfahmi.myjetpackprosubmission.repositories.local

import androidx.paging.DataSource
import com.mfahmi.myjetpackprosubmission.db.BookmarkDao
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val myBookmarkDao: BookmarkDao) {

    companion object {
        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(myBookmarkDao: BookmarkDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(myBookmarkDao)
            }
    }

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getMovieFromDb(): DataSource.Factory<Int, MovieEntity> = myBookmarkDao.getMovie()

    fun getTvShowFromDb(): DataSource.Factory<Int, TvShowEntity> = myBookmarkDao.getTvShow()

    fun insertMovieToDB(movies: List<MovieEntity>) =
        executorService.execute { myBookmarkDao.insertMovies(movies) }

    fun insertTvShowToDB(tvShows: List<TvShowEntity>) =
        executorService.execute { myBookmarkDao.insertTvShow(tvShows) }

    fun deleteMovieFromDb(movieEntity: MovieEntity) =
        myBookmarkDao.deleteMovieBookmarked(movieEntity)

    fun deleteTvShowFromDb(tvShowEntity: TvShowEntity) =
        myBookmarkDao.deleteTvShowBookmarked(tvShowEntity)

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

}