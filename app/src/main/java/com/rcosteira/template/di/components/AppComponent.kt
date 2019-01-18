package com.rcosteira.template.di.components

import android.content.Context
import com.rcosteira.template.App
import com.rcosteira.template.di.modules.ActivitiesModule
import com.rcosteira.template.di.modules.DataModule
import com.rcosteira.template.di.modules.ViewModelModule
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