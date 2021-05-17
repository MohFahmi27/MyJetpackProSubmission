package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetDetailMovieCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.GetMoviesCallback
import com.mfahmi.myjetpackprosubmission.repositories.remote.RemoteDataSource
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseMovie

class FakeMoviesRepository(private val remoteDataSource: RemoteDataSource) {

    fun getMoviesData(): LiveData<List<ResponseMovie>> {
        val moviesData = MutableLiveData<List<ResponseMovie>>()
        remoteDataSource.getPopularMovies(object : GetMoviesCallback {
            override fun onResponse(responseMovies: List<ResponseMovie>) {
                moviesData.postValue(responseMovies)
            }
        })
        return moviesData
    }

    fun getDetailMoviesData(movieId: Int): LiveData<ResponseDetailMovie> {
        val movieDetailData = MutableLiveData<ResponseDetailMovie>()
        remoteDataSource.getDetailMovie(movieId, object : GetDetailMovieCallback {
            override fun onResponse(responseDetailMovie: ResponseDetailMovie) {
                movieDetailData.postValue(responseDetailMovie)
            }
        })
        return movieDetailData
    }

}