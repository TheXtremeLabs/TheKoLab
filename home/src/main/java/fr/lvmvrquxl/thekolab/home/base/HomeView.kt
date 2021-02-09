package fr.lvmvrquxl.thekolab.home.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarAdapter
import fr.lvmvrquxl.thekolab.home.toolbar.IToolbarCallback
import fr.lvmvrquxl.thekolab.home.toolbar.IToolbarListener
import fr.lvmvrquxl.thekolab.home.toolbar.time.view.ToolbarTimeFragment
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherFragment
import fr.lvmvrquxl.thekolab.shared.permission.Permission
import fr.lvmvrquxl.thekolab.shared.permission.PermissionBuilder
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Home page view.
 *
 * This class is responsible for managing the views of the home page activity.
 *
 * @param activity Instance of the home page activity
 *
 * @since 0.1.3
 *
 * @see [ActivityView]
 * @see [AppCompatActivity]
 * @see [HomeActivity]
 */
internal class HomeView private constructor(private val activity: AppCompatActivity) :
    ActivityView<HomeActivityBinding>() {
    companion object {
        /**
         * Create the home page view.
         *
         * @param activity Instance of the home page activity
         *
         * @return New instance of the home page view
         *
         * @since 0.1.3
         *
         * @see [AppCompatActivity]
         * @see [ActivityView]
         */
        fun create(activity: AppCompatActivity): ActivityView<HomeActivityBinding> =
            HomeView(activity)
    }

    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var permissions: List<Permission>? = null
    private var toolbar: ToolbarBinding? = null

    override fun onCreate() = this.bindViews()

    override fun onDestroy() {
        this.collapsingToolbar = null
        this.permissions = null
        this.toolbar = null
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(grantResults: IntArray) {
        this.permissions?.forEach { permission: Permission ->
            permission.checkGrantResults(grantResults)
        }
        this.setViewPager()
    }

    override fun onStart() {
        this.setStatusBarTransparent()
        this.initAppBar()
        this.initPermissions()
        this.checkPermissions()
        this.setViewPager()
    }

    private fun arePermissionsGranted(): Boolean =
        true == this.permissions?.all { p: Permission -> p.isGranted() }

    private fun bindViews() {
        super.viewBinding = HomeActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun checkPermissions() = this.permissions?.forEach { p: Permission -> p.check() }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        appBar?.setExpanded(true)
        val callback: IToolbarCallback? = this.toolbar?.let { toolbar: ToolbarBinding ->
            IToolbarCallback.create(this.activity, toolbar)
        }
        val toolbarListener: IToolbarListener? =
            callback?.let { c: IToolbarCallback -> IToolbarListener.create(c) }
        appBar?.addOnOffsetChangedListener(toolbarListener)
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
        val fragments: MutableList<Fragment> = mutableListOf(ToolbarTimeFragment.create())
        if (this.arePermissionsGranted()) fragments.add(ToolbarWeatherFragment.create())
        this.toolbar?.viewPager?.let { viewPager: ViewPager2 ->
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager.adapter = ToolbarAdapter.create(this.activity, fragments)
            this.toolbar?.tabIndicators?.let { tabLayout: TabLayout ->
                TabLayoutMediator(tabLayout, viewPager) { _: TabLayout.Tab, _: Int -> }.attach()
            }
        }
    }
}