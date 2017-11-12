package com.rcosteira.kotlintemplate

import android.app.Activity
import android.app.Application
import com.rcosteira.kotlintemplate.di.AppInjector
import com.rcosteira.kotlintemplate.utils.ReleaseTree
import com.sioslife.hidepixel.seninc.videocall.utils.LinkingDebugTree

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        injectDaggerComponents()

        if (BuildConfig.DEBUG) {
            Timber.plant(LinkingDebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    open fun injectDaggerComponents() {
        AppInjector.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}