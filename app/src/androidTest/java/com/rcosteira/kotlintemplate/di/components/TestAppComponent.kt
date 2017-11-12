package com.rcosteira.kotlintemplate.di.components

import com.rcosteira.kotlintemplate.TestApp
import com.rcosteira.kotlintemplate.di.modules.TestActivitiesModule
import com.rcosteira.kotlintemplate.di.modules.TestAppModule
import com.rcosteira.kotlintemplate.di.modules.TestDataModule
import com.rcosteira.kotlintemplate.di.modules.TestViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        TestActivitiesModule::class,
        TestViewModelModule::class,
        TestDataModule::class,
        TestAppModule::class
))
interface TestAppComponent : AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: TestApp): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApp)
}