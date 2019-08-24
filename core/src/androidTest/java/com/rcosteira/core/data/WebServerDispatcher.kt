package com.rcosteira.core.data

import android.content.Context
import com.rcosteira.core.data.api.ApiPaths
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStream

class WebServerDispatcher constructor(private val context: Context) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            // These paths should be store in a file shared by the Api interface
            ApiPaths.users -> MockResponse().setResponseCode(200).setBody(getJson("users.json"))
            "${ApiPaths.users}/mojombo" -> MockResponse().setResponseCode(200).setBody(getJson("mojombo.json"))
            "${ApiPaths.users}/defunkt" -> MockResponse().setResponseCode(200).setBody(getJson("defunkt.json"))
            else -> MockResponse().setResponseCode(404)
        }
    }

    private fun getJson(path: String): String {
        val jsonStream: InputStream = context.assets.open("networkResponses/$path")
        return String(jsonStream.readBytes())
    }
}