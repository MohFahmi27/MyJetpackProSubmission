package com.mfahmi.myjetpackprosubmission.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mfahmi.myjetpackprosubmission.repositories.remote.ApiResponse
import com.mfahmi.myjetpackprosubmission.repositories.remote.StatusApi.*
import com.mfahmi.myjetpackprosubmission.utils.AppExecutors
import com.mfahmi.myjetpackprosubmission.vo.ResourceValue

abstract class NetworkBoundResource<ResultType, RequestType>(private val myExecutors: AppExecutors) {

    private val result = MediatorLiveData<ResourceValue<ResultType>>()

    init {
        result.value = ResourceValue.loading(null)

        @Suppress("LeakingThis") val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = ResourceValue.success(newData)
                }
            }
        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val myApiResponse = createCall()

        result.addSource(dbSource) {
            result.value = ResourceValue.loading(it)
        }

        result.addSource(myApiResponse) { response ->
            result.removeSource(myApiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                SUCCESS -> myExecutors.diskIO().execute {
                    saveCallResult(response.body)
                    myExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = ResourceValue.success(newData)
                        }
                    }
                }
                EMPTY -> myExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = ResourceValue.success(newData)
                    }
                }
                ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = ResourceValue.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<ResourceValue<ResultType>> = result

}