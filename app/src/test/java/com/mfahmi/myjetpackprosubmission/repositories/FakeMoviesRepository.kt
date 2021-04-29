package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.models.movies.ResponseMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailMovieCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetMoviesCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteRepositories

class FakeMoviesRepository(private val remoteRepositories: RemoteRepositories) {

    fun getMoviesData(): LiveData<List<ResponseMovie>> {
        val moviesData = MutableLiveData<List<ResponseMovie>>()
        remoteRepositories.getPopularMovies(object : GetMoviesCallback {
            override fun onResponse(responseMovies: List<ResponseMovie>) {
                moviesData.postValue(responseMovies)
            }
        })
        return moviesData
    }

    fun getDetailMoviesData(movieId: Int): LiveData<ResponseDetailMovie> {
        val movieDetailData = MutableLiveData<ResponseDetailMovie>()
        remoteRepositories.getDetailMovie(movieId, object : GetDetailMovieCallback {
            override fun onResponse(responseDetailMovie: ResponseDetailMovie) {
                movieDetailData.postValue(responseDetailMovie)
            }
        })
        return movieDetailData
    }

}