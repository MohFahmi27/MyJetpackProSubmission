package com.mfahmi.myjetpackprosubmission.utils

import org.mockito.ArgumentMatchers

object TestHelper {
    fun <T> anyOfT(type: Class<T>): T {
        return ArgumentMatchers.any(type)
    }

    fun <T> eqOfT(obj: T): T {
        return ArgumentMatchers.eq(obj)
    }
}