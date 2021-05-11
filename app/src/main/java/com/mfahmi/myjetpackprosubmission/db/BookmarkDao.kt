package com.mfahmi.myjetpackprosubmission.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.MovieEntity
import com.mfahmi.myjetpackprosubmission.repositories.local.entities.TvShowEntity

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM tb_movie")
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tb_tv_show")
    fun getBookmarkedTvShow(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieBookmark(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowBookmark(tvShowEntity: TvShowEntity)

    @Delete
    fun deleteMovieBookmarked(movieEntity: MovieEntity)

    @Delete
    fun deleteTvShowBookmarked(tvShowEntity: TvShowEntity)

}