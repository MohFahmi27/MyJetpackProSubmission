package com.mfahmi.myjetpackprosubmission.repositories.remote

import android.util.Log
import com.mfahmi.myjetpackprosubmission.api.ApiConfig
import com.mfahmi.myjetpackprosubmission.api.ApiKey
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseMovie
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseItemTvShows
import com.mfahmi.myjetpackprosubmission.models.tvshow.ResponseTvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepositories private constructor(private val myApiConfig: ApiConfig) {

    companion object {
        private const val TAG = "RemoteRepositories"
        private const val API_KEY = ApiKey.API_KEY

        val getInstance by lazy { RemoteRepositories(ApiConfig) }

    }

    fun getPopularMovies(getMoviesCallback: GetMoviesCallback) {
        myApiConfig.apiService.getPopularMovie(API_KEY).enqueue(object : Callback<ResponseItemMovies> {
            override fun onResponse(call: Call<ResponseItemMovies>, response: Response<ResponseItemMovies>) {
                getMoviesCallback.onResponse(response.body()?.results as List<ResponseMovie>)
            }

            override fun onFailure(call: Call<ResponseItemMovies>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

    fun getDetailMovie(movieId: Int, getDetailMovieCallback: GetDetailMovieCallback) {
        myApiConfig.apiService.getDetailMovie(movieId, API_KEY).enqueue(object : Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                getDetailMovieCallback.onResponse(response.body() as ResponseDetailMovie)
            }

            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

    fun getPopularTvShows(getTvShowsCallback: GetTvShowsCallback) {
        myApiConfig.apiService.getPopularTvShow(API_KEY).enqueue(object : Callback<ResponseItemTvShows> {
            override fun onResponse(call: Call<ResponseItemTvShows>, response: Response<ResponseItemTvShows>) {
                getTvShowsCallback.onResponse(response.body()?.results as List<ResponseTvShow>)
            }

            override fun onFailure(call: Call<ResponseItemTvShows>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.printStackTrace()}")
            }

        })
    }

    fun getDetailTvShow(tvShowId: Int, getDetailTvShowCallback: GetDetailTvShowCallback) {
        myApiConfig.apiService.getDetailTvShow(tvShowId, API_KEY).enqueue(object : Callback<ResponseDetailTvShow> {
            override fun onResponse(call: Call<ResponseDetailTvShow>, response: Response<ResponseDetailTvShow>) {
                getDetailTvShowCallback.onResponse(response.body() as ResponseDetailTvShow)
            }

            override fun onFailure(call: Call<ResponseDetailTvShow>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.printStackTrace()}")
            }
        })
    }

}

interface GetMoviesCallback {
    fun onResponse(responseMovies: List<ResponseMovie>)
}

interface GetTvShowsCallback {
    fun onResponse(responseTvShows: List<ResponseTvShow>)
}

interface GetDetailMovieCallback {
    fun onResponse(responseDetailMovie: ResponseDetailMovie)
}

interface GetDetailTvShowCallback {
    fun onResponse(responseDetailTvShow: ResponseDetailTvShow)
}