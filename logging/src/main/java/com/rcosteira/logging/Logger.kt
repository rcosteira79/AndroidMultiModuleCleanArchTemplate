package com.rcosteira.logging

import timber.log.Timber


object Logger {
    private val logger by lazy {
        TimberLogging()
    }

    fun init() {
        Timber.plant(logger)
    }

    fun d(message: String, t: Throwable? = null) = logger.d(t, message)

    fun i(message: String, t: Throwable? = null) = logger.i(t, message)

    fun e(t: Throwable? = null, message: String) = logger.e(t, message)

    fun wtf(t: Throwable? = null, message: String) = logger.wtf(t, message)
}