package fr.lvmvrquxl.thekolab.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private var view: HomeView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view = HomeView(this)
        super.setContentView(this.view?.root())
    }

    override fun onDestroy() {
        super.onDestroy()
        this.view?.onDestroy()
        this.view = null
    }
}