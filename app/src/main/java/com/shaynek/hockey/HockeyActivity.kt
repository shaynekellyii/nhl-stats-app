package com.shaynek.hockey

import android.os.Bundle
import android.preference.Preference
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shaynek.hockey.common.db.NO_ID_SET
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.di.AppInjector
import javax.inject.Inject

class HockeyActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.injector.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        if (prefs.getFavoriteTeam() != NO_ID_SET) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.startDestination = R.id.scheduleFragment
            navController.graph = navGraph
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setupWithNavController(navController)
    }
}
