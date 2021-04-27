package com.mfahmi.myjetpackprosubmission.api

import com.mfahmi.myjetpackprosubmission.models.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.models.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.models.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.models.ResponseItemTvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): ResponseItemMovies

    @GET("tv/popular")
    suspend fun getPopularTvShow(@Query("api_key") apiKey: String): ResponseItemTvShows

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ResponseDetailMovie>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ResponseDetailTvShow>
}