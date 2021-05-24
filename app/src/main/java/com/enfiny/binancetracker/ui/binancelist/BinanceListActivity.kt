package com.enfiny.binancetracker.ui.binancelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bibekluffy.binancetracker.R
import com.bibekluffy.binancetracker.databinding.ActivityBinanceListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BinanceListActivity : AppCompatActivity() {
    private lateinit var activityBinanceListBinding: ActivityBinanceListBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinanceListBinding = ActivityBinanceListBinding.inflate(layoutInflater)
        setContentView(activityBinanceListBinding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
        setupDrawerLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, activityBinanceListBinding.drawerLayout)
    }

    private fun setupDrawerLayout() {
        activityBinanceListBinding.mainDrawer.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (activityBinanceListBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityBinanceListBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}