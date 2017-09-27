package com.rcosteira.kotlintemplate.ui

import android.support.v4.app.FragmentManager
import com.rcosteira.kotlintemplate.R
import com.rcosteira.kotlintemplate.ui.menu.MenuFragment
import javax.inject.Inject

class NavigationController @Inject constructor(mainActivity: MainActivity) {

    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = mainActivity.supportFragmentManager

    fun navigateToMenu() {
        val menuFragment = MenuFragment()
        fragmentManager.beginTransaction()
                .replace(containerId, menuFragment)
                .commitAllowingStateLoss()
    }
}