package fr.lvmvrquxl.thekolab.home.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * Home page activity.
 *
 * This activity is managing the lifecycle of the home page.
 *
 * @since 0.1.3
 */
class HomeActivity : AppCompatActivity() {
    private var view: BaseView<HomeActivityBinding>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ToolbarWeatherPresenter.GPS_USABLE_REQUIRED == requestCode) this.startActivity(
            Intent(this, this::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    override fun onBackPressed() = this.finishAffinity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view = HomeView.create(this)
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