package com.rcosteira.kotlintemplate.di.modules

import android.content.Context
import com.rcosteira.kotlintemplate.TestApp
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideContext(app: TestApp): Context = app.applicationContext
}