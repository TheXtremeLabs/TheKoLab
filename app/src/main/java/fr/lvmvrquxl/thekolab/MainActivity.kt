package fr.lvmvrquxl.thekolab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.splashscreen.activity.SplashscreenActivity

/**
 * Main activity.
 *
 * This class is the entry point of the application.
 * It is responsible for loading the first view, which is currently [SplashscreenActivity].
 *
 * @since 1.0.0
 */
internal class MainActivity : AppCompatActivity() {
    companion object {
        private val splashscreen: Class<SplashscreenActivity> = SplashscreenActivity::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToSplashscreen()
    }

    private fun goToSplashscreen() {
        val intent = Intent(this, splashscreen)
        super.startActivity(intent)
        super.finishAffinity()
    }
}