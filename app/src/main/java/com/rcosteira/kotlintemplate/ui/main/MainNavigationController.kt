package com.rcosteira.kotlintemplate.ui.main

import com.rcosteira.kotlintemplate.R
import com.rcosteira.kotlintemplate.ui.main.menu.MenuFragment
import com.rcosteira.kotlintemplate.utils.addFragment
import javax.inject.Inject

class MainNavigationController @Inject constructor(private val mainActivity: MainActivity) {

    private val containerId: Int = R.id.container

    fun navigateToMenu() {
        val menuFragment = MenuFragment()

        mainActivity.addFragment(menuFragment, containerId)
    }
}