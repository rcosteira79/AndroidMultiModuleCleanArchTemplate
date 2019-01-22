package com.rcosteira.template

import android.app.Activity
import android.app.Application
import com.rcosteira.core.Core
import com.rcosteira.template.di.AppInjector
import com.rcosteira.logging.Logger
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Core.initAppCore(this)
        initDagger()
    }

    open fun initDagger() {
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}