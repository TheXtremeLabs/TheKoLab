package fr.lvmvrquxl.thekolab.home.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Home page activity.
 *
 * This activity is managing the lifecycle of the home page.
 *
 * @since 1.0.0
 */
class HomeActivity : AppCompatActivity() {
    private var view: ActivityView<HomeActivityBinding>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ToolbarWeatherPresenter.GPS_USABLE_REQUIRED == requestCode) this.startActivity(
            Intent(this, this::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

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
        this.view = HomeView.create(this).apply {
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