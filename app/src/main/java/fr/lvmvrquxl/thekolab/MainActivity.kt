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
 *
 * @see SplashscreenActivity
 */
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToSplashscreen()
    }

    private fun goToSplashscreen() {
        val intent = Intent(this, SplashscreenActivity.javaClass)
        super.startActivity(intent)
        super.finishAffinity()
    }
}