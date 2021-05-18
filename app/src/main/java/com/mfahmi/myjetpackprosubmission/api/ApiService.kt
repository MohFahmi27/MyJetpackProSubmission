package com.mfahmi.myjetpackprosubmission.api

import com.mfahmi.myjetpackprosubmission.data.remote.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.data.remote.models.tvshow.ResponseItemTvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<ResponseItemMovies>

    @GET("tv/popular")
    fun getPopularTvShow(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<ResponseItemTvShows>
}