package com.rcosteira.core

import android.app.Application
import com.rcosteira.logging.Logger
import com.squareup.leakcanary.LeakCanary


object Core {

    fun initAppCore(context: Application) {
        initCrashlytics(context)

        if (LeakCanary.isInAnalyzerProcess(context)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        LeakCanary.install(context)

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