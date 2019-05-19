package com.example.tabswithlists.di

import androidx.lifecycle.ViewModel
import com.example.tabswithlists.presentation.TabsWithListsFragment
import com.example.tabswithlists.presentation.TabsWithListsViewModel
import com.rcosteira.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TabsWithListsModule {
    @ContributesAndroidInjector
    abstract fun provideTabsWithListsFragmentFragment(): TabsWithListsFragment


    @Binds
    @IntoMap
    @ViewModelKey(TabsWithListsViewModel::class)
    abstract fun bindTabsWithListsViewModel(tabsWithListsViewModel: TabsWithListsViewModel): ViewModel
}