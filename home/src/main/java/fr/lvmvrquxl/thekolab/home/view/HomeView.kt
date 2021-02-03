package fr.lvmvrquxl.thekolab.home.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarListener
import fr.lvmvrquxl.thekolab.shared.permission.InternetPermission
import fr.lvmvrquxl.thekolab.shared.permission.LocationPermission
import fr.lvmvrquxl.thekolab.shared.permission.Permission
import fr.lvmvrquxl.thekolab.shared.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.view.BaseView

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
class HomeView(private val activity: AppCompatActivity) : BaseView<HomeActivityBinding>() {
    internal companion object {
        fun build(activity: AppCompatActivity): BaseView<HomeActivityBinding> = HomeView(activity)
    }

    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var permissions: List<Permission>? = null
    private var toolbar: HomeToolbarBinding? = null

    init {
        this.bindViews()
        this.initPermissions()
        this.checkPermissions()
        this.initAppBar()
        this.setViewPager()
        this.setStatusBarTransparent()
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
    internal fun hideCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = " "
    }

    override fun onRequestPermissionsResult(grantResults: IntArray) {
        this.permissions?.forEach { permission: Permission ->
            permission.checkGrantResults(grantResults)
        }
        this.setViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.collapsingToolbar = null
        this.permissions = null
        this.toolbar = null
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
    internal fun showCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = StringUtils.appName(this.activity)
    }

    private fun bindViews() {
        super.viewBinding = HomeActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun checkPermissions() =
        this.permissions?.forEach { permission: Permission -> permission.check() }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        val homeAppBarListener: HomeToolbarListener = HomeToolbarListener.build(this)
        this.hideCollapsingToolbarTitle()
        appBar?.setExpanded(true)
        appBar?.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initPermissions() {
        this.permissions =
            listOf(InternetPermission(this.activity), LocationPermission(this.activity))
    }

    private fun setStatusBarTransparent() {
        this.activity.window.statusBarColor = Color.TRANSPARENT
    }

    private fun setViewPager() {
        val fragments: List<Fragment> =
            when (this.permissions?.all { permission: Permission -> permission.isGranted() }) {
                true -> listOf(HomeToolbarTimeFragment(), HomeToolbarWeatherFragment())
                else -> listOf(HomeToolbarTimeFragment())
            }
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager?.adapter = HomeToolbarAdapter(
            this.activity.supportFragmentManager,
            this.activity.lifecycle,
            fragments
        )
    }
}