package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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

    private var view: LifecycleObserver? = null
    private var viewModel: SplashscreenViewModel? = null

    init {
        this.initView()
        this.initViewModel()
    }

    override fun onBackPressed() {}

    override fun onDestroy() {
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    private fun addObserver(observer: LifecycleObserver) = this.lifecycle.addObserver(observer)

    private fun destroyView() {
        this.view?.let { view: LifecycleObserver -> this.removeObserver(view) }
        this.view = null
    }

    private fun destroyViewModel() {
        this.viewModel?.let { viewModel: SplashscreenViewModel -> this.removeObserver(viewModel) }
        this.viewModel = null
    }

    private fun goToHome() {
        val intent = Intent(this, homeActivity)
        super.startActivity(intent)
        super.finishAffinity()
    }

    private fun initView() {
        this.view = SplashscreenView.create(this)
        this.view?.let { view: LifecycleObserver -> this.addObserver(view) }
    }

    private fun initViewModel() {
        this.viewModel = SplashscreenViewModel.instance()
        this.observeViewModel()
        this.viewModel?.let { viewModel: SplashscreenViewModel -> this.addObserver(viewModel) }
    }

    private fun observeViewModel() =
        this.viewModel?.state?.observe(this) { state: SplashscreenState ->
            if (SplashscreenState.CLOSABLE == state) this.goToHome()
        }

    private fun removeObserver(observer: LifecycleObserver) =
        this.lifecycle.removeObserver(observer)
}