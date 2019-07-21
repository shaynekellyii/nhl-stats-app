package com.shaynek.hockey

import android.os.Bundle
import android.preference.Preference
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.shaynek.hockey.common.db.NO_ID_SET
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.di.AppInjector
import javax.inject.Inject

class HockeyActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Preferences

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_games -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_standings -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.injector.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (prefs.getFavoriteTeam() != NO_ID_SET) {
            val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHost.navController
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.startDestination = R.id.standingsFragment
            navController.graph = navGraph
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
