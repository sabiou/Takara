package dev.farouk.takara

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import dev.farouk.takara.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding

    internal companion object {
        private const val SPLASH_DELAY = 5000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setEdgeToEdge()
        //binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_launcher)
        Handler().postDelayed({
            this.goToActivity(this, MainActivity::class.java)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            // close this activity
            finish()
        }, SPLASH_DELAY)
    }

    private fun setEdgeToEdge() {
        binding.root.setEdgeToEdgeSystemUiFlags()
    }
}