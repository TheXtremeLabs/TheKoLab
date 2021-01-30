package fr.lvmvrquxl.thekolab.home.view

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.base.permission.LocationPermission
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarListener
import fr.lvmvrquxl.thekolab.utils.Strings

/**
 * Home page view.
 *
 * This class is the view of the home page activity.
 *
 * @param activity Instance of the home page activity
 *
 * @constructor Bind views, init application bar and view pager
 *
 * @since 0.1.3
 * @see [HomeActivity]
 */
class HomeView(private val activity: HomeActivity) : BaseView<HomeActivityBinding>() {
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var locationPermission: LocationPermission? = null
    private var toolbar: HomeToolbarBinding? = null
    private var viewPagerFragments: List<Fragment> = listOf()

    init {
        this.bindViews()
        this.initAppBar()
        this.checkLocationPermission()
        this.initViewPager()
        this.setStatusBarTransparent()
    }

    override fun onRequestPermissionsResult(grantResults: IntArray) {
        this.locationPermission?.checkGrantResults(grantResults)
        this.setViewPagerFragments()
        this.toolbar?.let { it.viewPager.adapter?.notifyDataSetChanged() }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.collapsingToolbar = null
        this.locationPermission = null
        this.toolbar = null
    }

    /**
     * Hide the collapsing toolbar's title.
     *
     * This method hide the collapsing toolbar's title by setting its value to `" "`.
     * For showing the collapsing toolbar's title, consider using [showCollapsingToolbarTitle]
     * instead.
     *
     * @since 0.1.3
     * @see [showCollapsingToolbarTitle]
     */
    fun hideCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = " "
    }

    /**
     * Show the collapsing toolbar's title.
     *
     * This method show the collapsing toolbar's title, which is the application's name.
     * For hiding the collapsing toolbar's title, consider using [hideCollapsingToolbarTitle]
     * instead.
     *
     * @since 0.1.3
     * @see [hideCollapsingToolbarTitle]
     */
    fun showCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = Strings.appName(this.activity)
    }

    private fun bindViews() {
        super.viewBinding = HomeActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun checkLocationPermission() {
        this.locationPermission = LocationPermission(this.activity)
        this.locationPermission?.check()
    }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        val homeAppBarListener: HomeToolbarListener = HomeToolbarListener.build(this)
        this.hideCollapsingToolbarTitle()
        appBar?.setExpanded(true)
        appBar?.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initViewPager() {
        this.setViewPagerFragments()
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager?.adapter = HomeToolbarAdapter(
            this.activity.supportFragmentManager,
            this.activity.lifecycle,
            this.viewPagerFragments
        )
    }

    private fun setStatusBarTransparent() {
        this.activity.window.statusBarColor = Color.TRANSPARENT
    }

    private fun setViewPagerFragments() {
        val toolbarTimeFragment = HomeToolbarTimeFragment()
        val toolbarWeatherFragment = HomeToolbarWeatherFragment()
        this.viewPagerFragments = when (this.locationPermission?.isGranted()) {
            true -> listOf(toolbarTimeFragment, toolbarWeatherFragment)
            else -> listOf(toolbarTimeFragment)
        }
    }
}