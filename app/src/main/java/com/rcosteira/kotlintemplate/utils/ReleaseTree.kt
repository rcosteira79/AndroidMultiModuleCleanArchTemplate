package com.rcosteira.kotlintemplate.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String, priority: Int): Boolean {
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
    }

    override fun log(priority: Int, tag: String, message: String, t: Throwable) {

        if (isLoggable(tag, priority)) {
            if (message.length < MAX_LOG_LENGTH) {
                print(priority, tag, message, t)
            } else {
                var i = 0
                val length = message.length
                while (i < length) {
                    var nNewLine = message.indexOf('\n', i)
                    nNewLine = if (nNewLine != -1) nNewLine else length

                    do {
                        val nEnd = Math.min(nNewLine, i + MAX_LOG_LENGTH)
                        val strPart = message.substring(i, nEnd)
                        print(priority, tag, strPart, t)
                        i = nEnd
                    } while (i < nNewLine)
                    i++
                }
            }
        }
    }

    private fun print(nPriority: Int, strTag: String, strMessage: String, t: Throwable) {

        if (nPriority == Log.ASSERT) {
            Log.wtf(strTag, strMessage, t)
        } else {
            Log.println(nPriority, strTag, strMessage)
        }
    }

    companion object {

        private val MAX_LOG_LENGTH = 4096
    }
}