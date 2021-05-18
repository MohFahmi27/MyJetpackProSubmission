package com.mfahmi.myjetpackprosubmission.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfahmi.myjetpackprosubmission.api.ApiConfig
import com.mfahmi.myjetpackprosubmission.data.remote.models.movies.ResponseItemMovies
import com.mfahmi.myjetpackprosubmission.data.remote.models.tvshow.ResponseItemTvShows
import com.mfahmi.myjetpackprosubmission.utils.EspressoIdlingResource
import com.mfahmi.myjetpackprosubmission.utils.SingletonHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class RemoteDataSource(private val myApiConfig: ApiConfig) {

    companion object : SingletonHolder<RemoteDataSource, ApiConfig>(::RemoteDataSource)

    fun getPopularMovies(): LiveData<ApiResponse<ResponseItemMovies>> {
        EspressoIdlingResource.increment()
        val popularMovieList = MutableLiveData<ApiResponse<ResponseItemMovies>>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = myApiConfig.apiService.getPopularMovie().awaitResponse()
            try {
                if (result.isSuccessful) {
                    result.body()?.let {
                        popularMovieList.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(result.message(), result.body())
                }
            } catch (e: Exception) {
                ApiResponse.error(result.message(), result.body())
            }
            EspressoIdlingResource.decrement()
        }
        return popularMovieList
    }

    fun getPopularTvShows(): LiveData<ApiResponse<ResponseItemTvShows>> {
        EspressoIdlingResource.increment()
        val popularTvShowList = MutableLiveData<ApiResponse<ResponseItemTvShows>>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = myApiConfig.apiService.getPopularTvShow().awaitResponse()
            try {
                if (result.isSuccessful) {
                    result.body()?.let {
                        popularTvShowList.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(result.message(), result.body())
                }
            } catch (e: Exception) {
                ApiResponse.error(result.message(), result.body())
            }
            EspressoIdlingResource.decrement()
        }
        return popularTvShowList
    }
}