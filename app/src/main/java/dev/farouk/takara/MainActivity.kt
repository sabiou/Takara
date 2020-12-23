package dev.farouk.takara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import dev.farouk.takara.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
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
            .setDuration(15000)
            .setText("Le virus continue toujours de circuler Il et impératif de respecter les gestes barrières afin de réduire sa propagation. N'oubliez pas de porter votre masque le jour du scrutin")
            .show()
    }
}