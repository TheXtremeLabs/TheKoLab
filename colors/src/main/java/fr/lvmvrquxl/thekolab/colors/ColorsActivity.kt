package fr.lvmvrquxl.thekolab.colors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

class ColorsActivity : AppCompatActivity() {
    private var view: ActivityView<ColorsActivityBinding>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view = ColorsView.create(this)
        this.view?.onCreate()
        super.setContentView(this.view?.root)
    }

    override fun onDestroy() {
        this.view?.onDestroy()
        this.view = null
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.view?.onStart()
    }
}