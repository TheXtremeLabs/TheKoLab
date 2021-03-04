package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        private const val NEW_TASK: Int = Intent.FLAG_ACTIVITY_NEW_TASK

        private val homeActivity: Class<HomeActivity> = HomeActivity::class.java
    }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.CLOSABLE == state) this.goToHome()
        }

    private var view: ActivityView<SplashscreenActivityBinding>? = null
    private var viewModel: SplashscreenViewModel? = null

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
        this.createViewModel()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    override fun onPause() {
        this.pauseViewModel()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        this.resumeViewModel()
    }

    override fun onStart() {
        super.onStart()
        this.startViewModel()
    }

    override fun onStop() {
        this.stopViewModel()
        super.onStop()
    }

    private fun createView() {
        this.view = SplashscreenView.create(this).apply {
            this.onCreate()
            super.setContentView(this.root)
        }
    }

    private fun createViewModel() {
        this.viewModel = SplashscreenViewModel.instance().apply {
            this.onCreate()
            this.state.observe(this@SplashscreenActivity, this@SplashscreenActivity.stateObserver)
        }
    }

    private fun destroyView() {
        this.view = null
    }

    private fun destroyViewModel() {
        this.viewModel?.onDestroy()
        this.viewModel = null
    }

    private fun goToHome() {
        val intent: Intent = Intent(this, homeActivity).addFlags(NEW_TASK)
        super.startActivity(intent)
    }

    private fun pauseViewModel() = this.viewModel?.onPause()

    private fun resumeViewModel() = this.viewModel?.onResume()

    private fun startViewModel() = this.viewModel?.onStart()

    private fun stopViewModel() = this.viewModel?.onStop()
}