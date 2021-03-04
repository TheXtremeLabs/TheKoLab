package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.home.base.HomeActivity
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver
import fr.lvmvrquxl.thekolab.splashscreen.view.SplashscreenView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
class SplashscreenActivity : AppCompatActivity() {
    companion object {
        private val homeActivity: Class<HomeActivity> = HomeActivity::class.java
    }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.CLOSABLE == state) this.goToHome()
        }

    private val view: LifecycleObserver
        get() = SplashscreenView.create(this)

    private val viewModel: LifecycleObserver
        get() = SplashscreenViewModel.instance().apply {
            this.state.observe(this@SplashscreenActivity, this@SplashscreenActivity.stateObserver)
        }

    init {
        this.lifecycle.addObserver(this.view)
        this.lifecycle.addObserver(this.viewModel)
    }

    override fun onBackPressed() {}

    private fun goToHome() {
        val intent = Intent(this, homeActivity)
        super.startActivity(intent)
        super.finishAffinity()
    }
}