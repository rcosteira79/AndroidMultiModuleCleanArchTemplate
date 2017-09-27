package com.rcosteira.kotlintemplate.di.components

import com.rcosteira.kotlintemplate.App
import com.rcosteira.kotlintemplate.di.modules.ActivitiesModule
import com.rcosteira.kotlintemplate.di.modules.AppModule
import com.rcosteira.kotlintemplate.di.modules.DataModule
import com.rcosteira.kotlintemplate.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        ViewModelModule::class,
        DataModule::class,
        AppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}