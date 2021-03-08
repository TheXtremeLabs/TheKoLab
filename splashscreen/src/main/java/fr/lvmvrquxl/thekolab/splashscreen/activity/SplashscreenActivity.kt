package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.home.base.HomeActivity
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.splashscreen.view.SplashscreenView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

/**
 * Splashscreen's activity.
 *
 * @since 2.0.0
 */
class SplashscreenActivity : Activity() {
    companion object {
        /**
         * Class of the splashscreen's activity.
         *
         * @since 2.0.0
         */
        val javaClass: Class<SplashscreenActivity> = SplashscreenActivity::class.java
    }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.CLOSE == state) this.goToHome()
        }

    init {
        SplashscreenView.observe(this)
        SplashscreenViewModel.instance.let { viewModel: SplashscreenViewModel ->
            viewModel.observe(this)
            viewModel.state.observe(this, this.stateObserver)
        }
    }

    override fun onBackPressed() {}

    private fun goToHome() {
        val intent = Intent(this, HomeActivity.javaClass)
        super.startActivity(intent)
        super.finishAffinity()
    }
}