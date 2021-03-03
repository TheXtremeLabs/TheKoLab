package fr.lvmvrquxl.thekolab.splashscreen.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding

class SplashscreenActivity : AppCompatActivity() {
    private var view: ActivityView<SplashscreenActivityBinding>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
    }

    override fun onDestroy() {
        this.destroyView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.startView()
    }

    private fun createView() {
        this.view = SplashscreenView.create(this).apply {
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