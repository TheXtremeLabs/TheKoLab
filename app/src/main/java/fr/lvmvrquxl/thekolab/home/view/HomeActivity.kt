package fr.lvmvrquxl.thekolab.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarWeatherPresenter

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
        if (HomeToolbarWeatherPresenter.REQUEST_CHECK_SETTINGS == requestCode)
            this.startActivity(Intent(this, this::class.java))
    }

    override fun onBackPressed() = this.finishAffinity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view = HomeView(this)
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