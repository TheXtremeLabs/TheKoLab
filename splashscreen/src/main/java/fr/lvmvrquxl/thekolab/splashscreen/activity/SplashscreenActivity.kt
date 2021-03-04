package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        private val tag: String = SplashscreenActivity::class.java.simpleName
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
        this.observeState()
    }

    override fun onDestroy() {
        this.log("onDestroy")
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    override fun onPause() {
        this.log("onPause")
        this.pauseViewModel()
        super.onPause()
    }

    override fun onStart() {
        this.log("onStart")
        super.onStart()
        this.startView()
        this.startViewModel()
    }

    override fun onStop() {
        this.log("onStop")
        super.onStop()
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

    private fun goToHome() {
        val intent: Intent = Intent(this, homeActivity).addFlags(NEW_TASK)
        super.startActivity(intent)
    }

    // TODO: Remove after testings
    private fun log(text: String) = Log.d(tag, text)

    private fun observeState() =
        this.viewModel?.state?.observe(this) { state: SplashscreenState ->
            if (SplashscreenState.CLOSABLE == state) this.goToHome()
        }

    private fun pauseViewModel() = this.viewModel?.onStop()

    private fun startView() = this.view?.onStart()

    private fun startViewModel() = this.viewModel?.onStart()
}