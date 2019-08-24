package com.rcosteira.core.data

import android.content.Context
import okhttp3.mockwebserver.MockWebServer

object MockWebServerSetup {
    fun getMockWebServer(context: Context): MockWebServer {
        val webServer = MockWebServer()
        webServer.start(8080)
        webServer.dispatcher = WebServerDispatcher(context)

        return webServer
    }
}