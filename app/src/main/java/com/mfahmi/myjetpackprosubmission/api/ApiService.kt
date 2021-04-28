package com.mfahmi.myjetpackprosubmission.api

import com.mfahmi.myjetpackprosubmission.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseItemTvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String): Call<ResponseItemMovies>

    @GET("tv/popular")
    fun getPopularTvShow(@Query("api_key") apiKey: String): Call<ResponseItemTvShows>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ResponseDetailMovie>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ResponseDetailTvShow>
}