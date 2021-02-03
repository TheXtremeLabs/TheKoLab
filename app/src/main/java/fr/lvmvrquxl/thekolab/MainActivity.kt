package fr.lvmvrquxl.thekolab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.view.HomeActivity

/**
 * Main activity.
 *
 * This class is the entry point of the application.
 * It is responsible for loading the first view, which is currently [HomeActivity].
 *
 * @since 0.1.3
 */
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.goToHome()
    }

    private fun goToHome() = this.startActivity(HomeActivity.intent(this))
}