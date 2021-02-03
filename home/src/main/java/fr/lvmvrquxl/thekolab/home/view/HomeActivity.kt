package fr.lvmvrquxl.thekolab.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * Home page activity.
 *
 * This class is currently the main activity of the application.
 * It should only be used for overriding lifecycle tasks from Android's activities.
 *
 * @since 0.1.3
 */
class HomeActivity : AppCompatActivity() {
    private var view: BaseView<HomeActivityBinding>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (HomeToolbarWeatherPresenter.GPS_USABLE_REQUIRED == requestCode) this.recreate()
    }

    override fun onBackPressed() = this.finishAffinity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view = HomeView.build(this)
        super.setContentView(this.view?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.view?.onDestroy()
        this.view = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        this.view?.onRequestPermissionsResult(grantResults)
    }
}