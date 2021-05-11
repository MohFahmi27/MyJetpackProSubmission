package com.mfahmi.myjetpackprosubmission.utils

open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            if (instance != null) {
                instance as T
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}