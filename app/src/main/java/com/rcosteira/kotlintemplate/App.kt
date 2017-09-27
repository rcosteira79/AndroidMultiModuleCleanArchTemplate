package com.rcosteira.kotlintemplate

import android.app.Activity
import android.app.Application
import com.rcosteira.kotlintemplate.di.AppInjector
import com.rcosteira.kotlintemplate.utils.ReleaseTree

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}