package fr.lvmvrquxl.thekolab.splashscreen.activity

import android.content.Intent
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

    private var viewModel: SplashscreenViewModel? = null

    override fun initView() = SplashscreenView.observe(super.reference)

    override fun initViewModel() {
        this.viewModel =
            SplashscreenViewModel.instance.apply { this.observe(this@SplashscreenActivity) }
    }

    override fun observeViewModelState() {
        this.viewModel?.state?.observe(this) { state: SplashscreenState ->
            if (SplashscreenState.CLOSE == state) this.goToHome()
        }
    }

    override fun onBackPressed() {}

    override fun onDestroy() {
        this.destroyViewModel()
        super.onDestroy()
    }

    private fun destroyViewModel() {
        this.viewModel = null
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity.javaClass)
        super.startActivity(intent)
        super.finishAffinity()
    }
}