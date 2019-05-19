package com.rcosteira.template

import android.app.Activity
import android.app.Application
import com.rcosteira.core.Core
import com.rcosteira.template.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

// If More android injectors are needed, consider using DaggerApplication instead
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Core.initAppCore(this)
        initDagger()
    }

    private fun initDagger() {
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}