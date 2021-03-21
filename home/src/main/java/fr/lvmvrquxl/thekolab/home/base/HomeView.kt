package fr.lvmvrquxl.thekolab.home.base

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import fr.lvmvrquxl.thekolab.colors.activity.ColorsActivity
import fr.lvmvrquxl.thekolab.home.app.AppAdapter
import fr.lvmvrquxl.thekolab.home.app.model.App
import fr.lvmvrquxl.thekolab.home.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.home.toolbar.ToolbarAdapter
import fr.lvmvrquxl.thekolab.home.toolbar.IToolbarCallback
import fr.lvmvrquxl.thekolab.home.toolbar.IToolbarListener
import fr.lvmvrquxl.thekolab.home.toolbar.time.view.ToolbarTimeFragment
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherFragment
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.permission.Permission
import fr.lvmvrquxl.thekolab.shared.permission.PermissionBuilder
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Home page view.
 *
 * This class is responsible for managing the views of the home page activity.
 *
 * @since 1.0.0
 */
internal class HomeView private constructor(private val activityReference: ActivityReference) :
    ActivityView<HomeActivityBinding>(activityReference) {
    companion object {
        /**
         * Create the home page view.
         *
         * @param activityReference Reference of the home page activity
         *
         * @return New instance of the home page view
         *
         * @since 2.0.0
         */
        fun create(activityReference: ActivityReference): ActivityView<HomeActivityBinding> =
            HomeView(activityReference)
    }

    private val apps: List<App> = mutableListOf(
        App("Colors", "Change colors programmatically", ColorsActivity.javaClass)
    )
    private val appAdapter: AppAdapter = AppAdapter(this.apps)
    private var appsRecyclerView: RecyclerView? = null
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var permissions: List<Permission>? = null
    private var toolbar: ToolbarBinding? = null

    override fun bindView() {
        this.activityReference.get()?.let { activity: Activity ->
            super.viewBinding = HomeActivityBinding.inflate(activity.layoutInflater)
        }
        this.toolbar = super.viewBinding?.homeToolbar
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
        this.appsRecyclerView = super.viewBinding?.appList?.appsRecyclerView
    }

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
        this.applyAppsRecyclerView()
    }

    private fun applyAppsRecyclerView() {
        this.appsRecyclerView?.apply {
            this@HomeView.activityReference.get()?.let { activity: Activity ->
                this.layoutManager = LinearLayoutManager(activity)
            }
            this.adapter = this@HomeView.appAdapter
        }
    }

    private fun arePermissionsGranted(): Boolean =
        true == this.permissions?.all { p: Permission -> p.isGranted() }

    private fun checkPermissions() = this.permissions?.forEach { p: Permission -> p.check() }

    private fun initAppBar() = this.toolbar?.let { binding: ToolbarBinding ->
        val appBar: AppBarLayout = binding.root
        appBar.setExpanded(true)
        val callback: IToolbarCallback = IToolbarCallback.create(this.activityReference, binding)
        val toolbarListener: IToolbarListener = IToolbarListener.create(callback)
        appBar.addOnOffsetChangedListener(toolbarListener)
        this.activityReference.get()?.let { activity: Activity ->
            binding.versionName.text = SharedStringUtils.versionName(activity)
        }
    }

    private fun initPermissions() {
        this.permissions = PermissionBuilder.create(this.activityReference)
            .withInternet()
            .withLocation()
            .build()
    }

    private fun setStatusBarTransparent() {
        this.activityReference.get()?.window?.statusBarColor = Color.TRANSPARENT
    }

    private fun setViewPager() {
        val fragments: MutableList<Fragment> = mutableListOf(ToolbarTimeFragment.create())
        if (this.arePermissionsGranted()) fragments.add(ToolbarWeatherFragment.create())
        this.toolbar?.viewPager?.let { viewPager: ViewPager2 ->
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            ToolbarAdapter.create(this.activityReference, fragments)
                ?.let { adapter: FragmentStateAdapter -> viewPager.adapter = adapter }
            this.toolbar?.tabIndicators?.let { tabLayout: TabLayout ->
                TabLayoutMediator(tabLayout, viewPager) { _: TabLayout.Tab, _: Int -> }.attach()
            }
        }
    }
}