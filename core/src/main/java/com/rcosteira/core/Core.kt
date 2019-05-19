package com.rcosteira.core

import android.app.Application
import com.rcosteira.logging.Logger


object Core {

    fun initAppCore(context: Application) {
        initCrashlytics(context)

        Logger.init()
    }

    private fun initCrashlytics(context: Application) {
        /* val fabric = Fabric.Builder(context)
             .kits(Crashlytics())
             .debuggable(BuildConfig.DEBUG)
             .build()

         Fabric.with(fabric)

         FirebaseApp.initializeApp(context, FirebaseOptions.fromResource(context))*/
    }
}