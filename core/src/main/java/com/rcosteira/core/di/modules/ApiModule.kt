package com.rcosteira.core.di.modules

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.di.scopes.ActivityScope
import com.rcosteira.logging.Logger
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class ApiModule {

    @Binds
    @ActivityScope
    abstract fun provideApi(githubApi: GithubApi): Api

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Logger.i(message)
                }
            })

            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            return interceptor
        }
    }
}