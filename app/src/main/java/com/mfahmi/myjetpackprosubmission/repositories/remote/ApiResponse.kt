package com.mfahmi.myjetpackprosubmission.repositories.remote

import com.mfahmi.myjetpackprosubmission.repositories.remote.StatusApi.*

class ApiResponse<T>(val status: StatusApi, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}