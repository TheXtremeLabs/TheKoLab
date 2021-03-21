package fr.lvmvrquxl.thekolab.home.base

import android.content.Intent
import android.os.Bundle
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Home page activity.
 *
 * This activity is managing the lifecycle of the home page.
 *
 * @since 2.0.0
 */
@Deprecated("Should be refactored for version 2.1.0")
class HomeActivity : Activity() {
    companion object {
        /**
         * Class of the home's activity.
         *
         * @since 2.0.0
         */
        val javaClass: Class<HomeActivity> = HomeActivity::class.java
    }

    private var view: ActivityView<HomeActivityBinding>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ToolbarWeatherPresenter.GPS_USABLE_REQUIRED == requestCode) this.startActivity(
            Intent(this, this::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    override fun initView() {}

    override fun initViewModel() {}

    override fun observeViewModelState() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
    }

    override fun onDestroy() {
        this.destroyView()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        this.view?.onRequestPermissionsResult(grantResults)
    }

    override fun onStart() {
        super.onStart()
        this.startView()
    }

    private fun createView() {
        this.view = HomeView.create(super.reference).apply {
            this.onCreate()
            super.setContentView(this.root)
        }
    }

    private fun destroyView() {
        this.view?.onDestroy()
        this.view = null
    }

    private fun startView() = this.view?.onStart()
}