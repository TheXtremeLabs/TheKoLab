package fr.lvmvrquxl.thekolab.splashscreen.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.base.HomeActivity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
class SplashscreenActivity : AppCompatActivity() {
    private var view: ActivityView<SplashscreenActivityBinding>? = null
    private var viewModel: SplashscreenViewModel? = null

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
        this.createViewModel()
        this.observeState()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.startView()
        this.startViewModel()
    }

    private fun createView() {
        this.view = SplashscreenView.create(this).apply {
            this.onCreate()
            super.setContentView(this.root)
        }
    }

    private fun createViewModel() {
        this.viewModel = SplashscreenViewModel.instance()
    }

    private fun destroyView() {
        this.view?.onDestroy()
        this.view = null
    }

    private fun destroyViewModel() {
        this.viewModel?.onDestroy()
        this.viewModel = null
    }

    private fun goToHome() = this.startActivity(
        Intent(this, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )

    private fun observeState() =
        this.viewModel?.state?.observe(this) { state: SplashscreenState ->
            if (SplashscreenState.CLOSABLE == state) this.goToHome()
        }

    private fun startView() = this.view?.onStart()

    private fun startViewModel() = this.viewModel?.onStart()
}