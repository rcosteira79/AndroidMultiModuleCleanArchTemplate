package com.rcosteira.androidkotlintemplate.ui.main

import com.rcosteira.androidkotlintemplate.R
import com.rcosteira.androidkotlintemplate.ui.main.menu.MenuFragment
import com.rcosteira.androidkotlintemplate.utils.addFragment
import javax.inject.Inject

class MainNavigationController @Inject constructor(private val mainActivity: MainActivity) {

    private val containerId: Int = R.id.container

    fun navigateToMenu() {
        val menuFragment = MenuFragment()

        mainActivity.addFragment(menuFragment, containerId)
    }
}