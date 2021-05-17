package com.mfahmi.myjetpackprosubmission.repositories.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.api.ApiConfig
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseDetailMovie
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseDetailTvShow
import com.mfahmi.myjetpackprosubmission.repositories.remote.models.tvshow.ResponseItemTvShows
import com.mfahmi.myjetpackprosubmission.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class RemoteDataSource(private val myApiConfig: ApiConfig) {

    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(myApiConfig: ApiConfig): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteDataSource(myApiConfig)
            }
    }

    fun getPopularMovies(): LiveData<ApiResponse<ResponseItemMovies>> {
        EspressoIdlingResource.increment()
        val popularMovieList = MutableLiveData<ApiResponse<ResponseItemMovies>>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = myApiConfig.apiService.getPopularMovie().awaitResponse()
            try {
                if (result.isSuccessful) {
                    result.body()?.let {
                        Log.d(TAG, "getPopularMovies: $it")
                        popularMovieList.postValue(ApiResponse.success(it))
                    }
                } else {
                    Log.d(TAG, "getPopularMovies: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "getPopularMovies: ${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return popularMovieList
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<ResponseDetailMovie>> {
        EspressoIdlingResource.increment()
        val detailMovieById = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
        CoroutineScope(Dispatchers.IO).launch {
            val resultDetail = myApiConfig.apiService.getDetailMovie(movieId).awaitResponse()
            try {
                if (resultDetail.isSuccessful) {
                    resultDetail.body()?.let {
                        detailMovieById.postValue(ApiResponse.success(it))
                    }
                } else {
                    Log.d(TAG, "getDetailMovie: ${resultDetail.code()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "getDetailMovie: ${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return detailMovieById
    }

    fun getPopularTvShows(): LiveData<ApiResponse<ResponseItemTvShows>> {
        EspressoIdlingResource.increment()
        val popularTvShowList = MutableLiveData<ApiResponse<ResponseItemTvShows>>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = myApiConfig.apiService.getPopularTvShow().awaitResponse()
            try {
                if (result.isSuccessful) {
                    result.body()?.let {
                        Log.d(TAG, "getPopularTvShows: $it")
                        popularTvShowList.postValue(ApiResponse.success(it))
                    }
                } else {
                    Log.d(TAG, "getPopularTvShows: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "getPopularTvShows: ${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return popularTvShowList
    }

    fun getDetailTvShow(tvShowId: Int): LiveData<ApiResponse<ResponseDetailTvShow>> {
        EspressoIdlingResource.increment()
        val detailTvShowById = MutableLiveData<ApiResponse<ResponseDetailTvShow>>()
        CoroutineScope(Dispatchers.IO).launch {
            val resultDetail = myApiConfig.apiService.getDetailTvShow(tvShowId).awaitResponse()
            try {
                if (resultDetail.isSuccessful) {
                    resultDetail.body()?.let {
                        detailTvShowById.postValue(ApiResponse.success(it))
                    }
                } else {
                    Log.d(TAG, "getDetailMovie: ${resultDetail.code()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "getDetailMovie: ${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return detailTvShowById
    }

}