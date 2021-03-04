package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.home.base.HomeActivity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
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

    private val viewModel: LifecycleObserver
        get() = SplashscreenViewModel.instance().apply {
            this.state.observe(this@SplashscreenActivity, this@SplashscreenActivity.stateObserver)
        }

    private var view: ActivityView<SplashscreenActivityBinding>? = null

    init {
        this.lifecycle.addObserver(this.viewModel)
    }

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
    }

    override fun onDestroy() {
        this.destroyView()
        super.onDestroy()
    }

    private fun createView() {
        this.view = SplashscreenView.create(this).apply {
            this.onCreate()
            super.setContentView(this.root)
        }
    }

    private fun destroyView() {
        this.view = null
    }

    private fun goToHome() {
        val intent = Intent(this, homeActivity)
        super.startActivity(intent)
        super.finishAffinity()
    }
}