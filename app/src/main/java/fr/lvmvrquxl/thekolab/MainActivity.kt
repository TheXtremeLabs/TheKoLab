package fr.lvmvrquxl.thekolab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.base.HomeActivity
import fr.lvmvrquxl.thekolab.splashscreen.SplashscreenActivity

/**
 * Main activity.
 *
 * This class is the entry point of the application.
 * It is responsible for loading the first view, which is currently [HomeActivity].
 *
 * @since 1.0.0
 */
internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.goToHome()
        this.goToSplashscreen()
    }

    // TODO: Only the splashscreen should invoke the home activity
    private fun goToHome() = this.startActivity(
        Intent(this, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )

    private fun goToSplashscreen() = this.startActivity(
        Intent(this, SplashscreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}