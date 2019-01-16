package com.rcosteira.androidkotlintemplate.di.components

import android.content.Context
import com.rcosteira.androidkotlintemplate.App
import com.rcosteira.androidkotlintemplate.di.modules.ActivitiesModule
import com.rcosteira.androidkotlintemplate.di.modules.DataModule
import com.rcosteira.androidkotlintemplate.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivitiesModule::class, ViewModelModule::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}