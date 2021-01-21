package fr.lvmvrquxl.thekolab.home.view

import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.utils.Strings
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarListener

class HomeView(private val activity: HomeActivity) {
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var toolbar: HomeToolbarBinding? = null
    private var viewBinding: HomeActivityBinding? = null

    init {
        this.bindViews()
        this.initAppBar()
        this.initViewPager()
        this.setStatusBarTransparent()
    }

    fun onDestroy() {
        this.collapsingToolbar = null
        this.toolbar = null
        this.viewBinding = null
    }

    fun hideCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = " "
    }

    fun root(): View? = this.viewBinding?.root

    fun showCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = Strings.appName(this.activity)
    }

    private fun bindViews() {
        this.viewBinding = HomeActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = this.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        val homeAppBarListener: HomeToolbarListener = HomeToolbarListener.build(this)
        this.hideCollapsingToolbarTitle()
        appBar?.setExpanded(true)
        appBar?.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initViewPager() {
        val toolbarTimeFragment = HomeToolbarTimeFragment()
        val fragments: List<Fragment> = listOf(toolbarTimeFragment)
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager?.adapter = HomeToolbarAdapter(
            this.activity.supportFragmentManager,
            this.activity.lifecycle,
            fragments
        )
    }

    private fun setStatusBarTransparent() {
        this.activity.window.statusBarColor = Color.TRANSPARENT
    }
}