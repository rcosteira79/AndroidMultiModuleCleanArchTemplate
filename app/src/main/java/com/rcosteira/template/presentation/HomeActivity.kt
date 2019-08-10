package com.rcosteira.template.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.rcosteira.template.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBar(navController, appBarConfiguration)
        setupBottomNavigationBar(navController)
    }

    private fun setupActionBar(navController: NavController, appBarConfiguration: AppBarConfiguration) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationBar(navController: NavController) {
        bottomNavigation.setupWithNavController(navController)
    }
}
