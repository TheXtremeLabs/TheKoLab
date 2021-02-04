package fr.lvmvrquxl.thekolab.home.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarBinding
import fr.lvmvrquxl.thekolab.home.toolbar.HomeToolbarAdapter
import fr.lvmvrquxl.thekolab.home.toolbar.HomeToolbarListener
import fr.lvmvrquxl.thekolab.home.toolbar.time.view.HomeToolbarTimeFragment
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.HomeToolbarWeatherFragment
import fr.lvmvrquxl.thekolab.shared.permission.Permission
import fr.lvmvrquxl.thekolab.shared.permission.PermissionBuilder
import fr.lvmvrquxl.thekolab.shared.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * Home page view.
 *
 * This class is responsible for managing the views of the home page activity.
 *
 * @param activity Instance of the home page activity
 *
 * @since 0.1.3
 * @see [HomeActivity]
 */
internal class HomeView(private val activity: AppCompatActivity) : BaseView<HomeActivityBinding>() {
    companion object {
        /**
         * Create the home page view.
         *
         * @param activity Instance of the home page activity
         *
         * @since 0.1.3
         */
        fun create(activity: AppCompatActivity): BaseView<HomeActivityBinding> = HomeView(activity)
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

    override fun onDestroy() {
        super.onDestroy()
        this.collapsingToolbar = null
        this.permissions = null
        this.toolbar = null
    }

    override fun onRequestPermissionsResult(grantResults: IntArray) {
        this.permissions?.forEach { permission: Permission ->
            permission.checkGrantResults(grantResults)
        }
        this.setViewPager()
    }

    /**
     * Hide the collapsing toolbar's title.
     *
     * This method hides the collapsing toolbar's title by setting its value to `" "`.
     * Consider using the [showCollapsingToolbarTitle] method for showing the collapsing toolbar's
     * title.
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
     * This method shows the collapsing toolbar's title, which is the application's name.
     * Consider using the [hideCollapsingToolbarTitle] method for hiding the collapsing toolbar's
     * title.
     *
     * @since 0.1.3
     * @see [hideCollapsingToolbarTitle]
     */
    fun showCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = StringUtils.appName(this.activity)
    }

    private fun arePermissionsGranted() = this.permissions?.all { p: Permission -> p.isGranted() }

    private fun bindViews() {
        super.viewBinding = HomeActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun checkPermissions() = this.permissions?.forEach { p: Permission -> p.check() }

    private fun initAppBar() {
        this.hideCollapsingToolbarTitle()
        val appBar: AppBarLayout? = this.toolbar?.root
        appBar?.setExpanded(true)
        val homeAppBarListener: HomeToolbarListener = HomeToolbarListener.build(this)
        appBar?.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initPermissions() {
        this.permissions = PermissionBuilder(this.activity)
            .withInternet()
            .withLocation()
            .build()
    }

    private fun setStatusBarTransparent() {
        this.activity.window.statusBarColor = Color.TRANSPARENT
    }

    private fun setViewPager() {
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val fragments: MutableList<Fragment> = mutableListOf(HomeToolbarTimeFragment.create())
        if (true == this.arePermissionsGranted()) fragments.add(HomeToolbarWeatherFragment.create())
        viewPager?.adapter = HomeToolbarAdapter.create(this.activity, fragments)
    }
}