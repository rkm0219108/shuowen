package com.rkm.tdd.shuowen

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutors @Inject constructor() {

    private val diskIO = Executors.newSingleThreadExecutor()
    private val networkIO = Executors.newFixedThreadPool(3)

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }
}
