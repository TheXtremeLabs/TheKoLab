package fr.lvmvrquxl.thekolab.home.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.home.databinding.ActivityBinding
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarAdapter
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarCallback
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarListener
import fr.lvmvrquxl.thekolab.home.toolbar.time.view.ToolbarTimeFragment
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherFragment
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
internal class View(private val activity: AppCompatActivity) : BaseView<ActivityBinding>() {
    companion object {
        /**
         * Create the home page view.
         *
         * @param activity Instance of the home page activity
         *
         * @since 0.1.3
         */
        fun create(activity: AppCompatActivity): BaseView<ActivityBinding> = View(activity)
    }

    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var permissions: List<Permission>? = null
    private var toolbar: ToolbarBinding? = null

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

    private fun arePermissionsGranted() = this.permissions?.all { p: Permission -> p.isGranted() }

    private fun bindViews() {
        super.viewBinding = ActivityBinding.inflate(this.activity.layoutInflater)
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun checkPermissions() = this.permissions?.forEach { p: Permission -> p.check() }

    private fun homeToolbarCallback(): ToolbarCallback = object : ToolbarCallback {
        private val toolbar: CollapsingToolbarLayout? = this@View.collapsingToolbar

        override fun hideTitle() {
            this.toolbar?.title = " "
        }

        override fun showTitle() {
            this.toolbar?.title = StringUtils.appName(this@View.activity)
        }
    }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        appBar?.setExpanded(true)
        val callback = this.homeToolbarCallback()
        val toolbarListener: ToolbarListener = ToolbarListener.create(callback)
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
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val fragments: MutableList<Fragment> = mutableListOf(ToolbarTimeFragment.create())
        if (true == this.arePermissionsGranted()) fragments.add(ToolbarWeatherFragment.create())
        viewPager?.adapter = ToolbarAdapter.create(this.activity, fragments)
    }
}