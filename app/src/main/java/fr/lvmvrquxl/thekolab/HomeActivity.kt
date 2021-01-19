package fr.lvmvrquxl.thekolab

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import fr.lvmvrquxl.thekolab.databinding.HomeActivityBinding
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarBinding

class HomeActivity : AppCompatActivity() {
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var toolbar: HomeToolbarBinding? = null
    private var viewBinding: HomeActivityBinding? = null

    fun hideCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = " "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view: View? = this.bindView()
        super.setContentView(view)
        this.initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.viewBinding = null
    }

    fun showCollapsingToolbarTitle() {
        this.collapsingToolbar?.title = Strings.appName(this)
    }

    private fun bindCollapsingToolbar() {
        this.collapsingToolbar = this.toolbar?.collapsingToolbar
    }

    private fun bindToolbar() {
        this.toolbar = this.viewBinding?.homeToolbar
    }

    private fun bindView(): View? {
        this.viewBinding = HomeActivityBinding.inflate(layoutInflater)
        return this.viewBinding?.root
    }

    private fun initAppBar() {
        val appBar: AppBarLayout? = this.toolbar?.root
        val homeAppBarListener = HomeToolbarListener(this)
        this.hideCollapsingToolbarTitle()
        appBar?.setExpanded(true)
        appBar?.addOnOffsetChangedListener(homeAppBarListener)
    }

    private fun initViewPager() {
        val toolbarTimeFragment = ToolbarTimeFragment()
        val fragments: List<Fragment> = listOf(toolbarTimeFragment)
        val viewPager: ViewPager2? = this.toolbar?.viewPager
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager?.adapter = HomeToolbarAdapter(supportFragmentManager, lifecycle, fragments)
    }

    private fun initViews() {
        this.bindToolbar()
        this.bindCollapsingToolbar()
        this.initAppBar()
        this.initViewPager()
        this.setStatusBarTransparent()
    }

    private fun setStatusBarTransparent() {
        window.statusBarColor = Color.TRANSPARENT
    }
}