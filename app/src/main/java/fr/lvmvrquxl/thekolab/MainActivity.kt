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
        private const val NEW_TASK: Int = Intent.FLAG_ACTIVITY_NEW_TASK
        private val splashscreen: Class<SplashscreenActivity> = SplashscreenActivity::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToSplashscreen()
    }

    private fun goToSplashscreen() {
        val intent: Intent = Intent(this, splashscreen).addFlags(NEW_TASK)
        super.startActivity(intent)
    }
}