package com.mfahmi.myjetpackprosubmission.vo

import com.mfahmi.myjetpackprosubmission.vo.Status.*

data class ResourceValue<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceValue<T> = ResourceValue(SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ResourceValue<T> = ResourceValue(ERROR, data, msg)

        fun <T> loading(data: T?): ResourceValue<T> = ResourceValue(LOADING, data, null)
    }
}