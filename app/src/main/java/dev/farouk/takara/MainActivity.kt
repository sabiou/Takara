package dev.farouk.takara

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import dev.farouk.takara.databinding.ActivityMainBinding
import dev.farouk.takara.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    private var isOnNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.darkThemeEnabled.observe(this) { nightModeActive ->
            this.isOnNightMode = nightModeActive

            val defaultMode = if (nightModeActive) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNav = binding.bottomNavigation
        val navGraphIds = listOf(
            R.navigation.nav_graph_calendar,
            R.navigation.nav_graph_candidates,
            R.navigation.nav_graph_results,
            R.navigation.nav_graph_info)
        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onStart() {
        super.onStart()
        showAlertCovid19()
    }

    // alert covid-19 message
    private fun showAlertCovid19() {
        Alerter.create(this)
            .setTitle("Info COVID-19 !")
            .setIcon(R.drawable.ic_baseline_coronavirus_24)
            .setBackgroundColorRes(R.color.orange_200)
            .setDuration(10000)
            .setText("Le virus continue toujours de circuler. Il et impératif de respecter les gestes barrières afin de réduire sa propagation. N'oubliez pas de porter votre masque le jour du scrutin")
            .show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.dayNightMode) {
            viewModel.toggleNightMode()
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (isOnNightMode) {
            menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.ic_night_mode)
        } else {
            menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.ic_light_mode)
        }
        return true
    }
}