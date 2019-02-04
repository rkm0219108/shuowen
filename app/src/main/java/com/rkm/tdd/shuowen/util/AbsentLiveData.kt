package com.rkm.tdd.shuowen.util

import androidx.lifecycle.LiveData

class AbsentLiveData<T> private constructor(value: T?) : LiveData<T>() {

    init {
        postValue(value)
    }

    companion object {
        fun <T> create(value: T? = null): LiveData<T> {
            return AbsentLiveData(value)
        }
    }
}
