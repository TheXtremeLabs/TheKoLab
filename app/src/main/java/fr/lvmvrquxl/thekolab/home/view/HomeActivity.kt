package fr.lvmvrquxl.thekolab.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding

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
}