package com.rcosteira.template.ui.main

import com.rcosteira.template.R
import com.rcosteira.template.ui.main.menu.MenuFragment
import com.rcosteira.template.utils.addFragment
import javax.inject.Inject

class MainNavigationController @Inject constructor(private val mainActivity: MainActivity) {

    private val containerId: Int = R.id.container

    fun navigateToMenu() {
        val menuFragment = MenuFragment()

        mainActivity.addFragment(menuFragment, containerId)
    }
}