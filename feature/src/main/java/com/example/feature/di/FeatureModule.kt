package com.example.feature.di

import androidx.lifecycle.ViewModel
import com.example.feature.presentation.FeatureFragment
import com.example.feature.presentation.FeatureViewModel
import com.rcosteira.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FeatureModule {
    @ContributesAndroidInjector
    abstract fun provideFeatureFragment(): FeatureFragment


    @Binds
    @IntoMap
    @ViewModelKey(FeatureViewModel::class)
    abstract fun bindWorkoutListViewModel(featureViewModel: FeatureViewModel): ViewModel
}