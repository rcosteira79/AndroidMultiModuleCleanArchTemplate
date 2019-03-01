package com.rcosteira.template.di.components

import android.content.Context
import com.example.feature.di.FeatureModule
import com.rcosteira.core.di.modules.ViewModelFactoryModule
import com.rcosteira.template.App
import com.rcosteira.template.di.modules.HomeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        HomeModule::class,
        ViewModelFactoryModule::class,
        FeatureModule::class
        //DataModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}