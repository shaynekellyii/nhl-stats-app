package com.shaynek.hockey

import android.os.Bundle
import android.preference.Preference
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isGone
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shaynek.hockey.common.db.NO_ID_SET
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.di.AppInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HockeyActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Preferences

    private val actionBarSize: Int by lazy {
        val styledAttrs = theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val size = styledAttrs.getDimension(0, 0f)
        styledAttrs.recycle()
        size.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.injector.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                toggleBottomBar(destination.id != R.id.selectTeamFragment)
                setToolbarTitle(destination.id)
            }
        }

        if (prefs.getFavoriteTeam() != NO_ID_SET) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.startDestination = R.id.scheduleFragment
            navController.graph = navGraph
        }

        nav_view.setupWithNavController(navController)
    }

    private fun setToolbarTitle(destinationId: Int) {
        activity_toolbar?.title = when (destinationId) {
            R.id.selectTeamFragment -> resources.getString(R.string.select_your_favorite_team)
            R.id.scheduleFragment -> resources.getString(R.string.schedule)
            R.id.standingsFragment -> resources.getString(R.string.standings)
            else -> ""
        }
    }

    private fun toggleBottomBar(isVisible: Boolean) {
        nav_view?.isGone = !isVisible
        nav_host_frame?.run {
            val newParams = (layoutParams as CoordinatorLayout.LayoutParams).apply {
                bottomMargin = if (isVisible) actionBarSize else 0
            }
            layoutParams = newParams
        }
    }
}
