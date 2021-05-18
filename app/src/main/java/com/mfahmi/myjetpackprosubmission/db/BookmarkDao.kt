package com.mfahmi.myjetpackprosubmission.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mfahmi.myjetpackprosubmission.data.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.data.local.entities.TvShowEntity

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM tb_movie")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_tv_show")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tb_movie WHERE is_bookmark = 1")
    fun getBookmarkedMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_tv_show WHERE is_bookmark = 1")
    fun getBookmarkedTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShowEntity: List<TvShowEntity>)

    @Update
    fun updateBookmarkMovie(movieEntity: MovieEntity)

    @Update
    fun updateBookmarkTvShow(tvShowEntity: TvShowEntity)

    @Query("SELECT is_bookmark FROM tb_movie WHERE movie_id = :movieId")
    fun checkMovieBookmark(movieId: Int): LiveData<Boolean>

    @Query("SELECT is_bookmark FROM tb_tv_show WHERE tv_show_id = :tvShowId")
    fun checkTvShowBookmark(tvShowId: Int): LiveData<Boolean>
}