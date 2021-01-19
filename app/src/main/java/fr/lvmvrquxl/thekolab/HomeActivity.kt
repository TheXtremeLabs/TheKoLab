package fr.lvmvrquxl.thekolab

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var activityBinding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activityBinding = HomeActivityBinding.inflate(layoutInflater)
        val view: View = this.activityBinding.root
        super.setContentView(view)
        this.initViews()
    }

    private fun initCollapsingToolbar() {
        val homeToolbar: HomeToolbarBinding = this.activityBinding.homeToolbar
        val appBar: AppBarLayout = homeToolbar.root
        val collapsingToolbar: CollapsingToolbarLayout = homeToolbar.collapsingToolbar
        val homeAppBarListener = HomeToolbarListener(this, collapsingToolbar)
        appBar.setExpanded(true)
        appBar.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initViews() {
        this.initCollapsingToolbar()
        this.setStatusBarTransparent()
    }

    private fun setStatusBarTransparent() {
        window.statusBarColor = Color.TRANSPARENT
    }
}