package fr.lvmvrquxl.thekolab.home.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.View
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
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarCallback
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarListener
import fr.lvmvrquxl.thekolab.home.toolbar.time.view.ToolbarTimeFragment
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherFragment
import fr.lvmvrquxl.thekolab.shared.permission.Permission
import fr.lvmvrquxl.thekolab.shared.permission.PermissionBuilder
import fr.lvmvrquxl.thekolab.shared.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

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
internal class HomeView(private val activity: AppCompatActivity) :
    ActivityView<HomeActivityBinding>() {
    companion object {
        /**
         * Create the home page view.
         *
         * @param activity Instance of the home page activity
         *
         * @since 0.1.3
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

    private fun homeToolbarCallback(): ToolbarCallback = object : ToolbarCallback {
        private val animationDuration: Int =
            this@HomeView.activity.resources.getInteger(android.R.integer.config_shortAnimTime)
        private val toolbar: ToolbarBinding? = this@HomeView.toolbar

        override fun hideTabIndicators() {
            this.toolbar?.tabIndicators?.apply {
                this.alpha = 0f
                this.visibility = View.GONE
            }
        }

        override fun hideTitle() {
            this.toolbar?.collapsingToolbar?.title = " "
        }

        override fun showTabIndicators() {
            this.toolbar?.tabIndicators?.apply {
                this.animate()
                    .alpha(0.75f)
                    .setDuration(animationDuration.toLong())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            this@apply.visibility = View.VISIBLE
                        }
                    })
            }
        }

        override fun showTitle() {
            this.toolbar?.collapsingToolbar?.title = StringUtils.appName(this@HomeView.activity)
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