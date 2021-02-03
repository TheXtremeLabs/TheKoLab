package fr.lvmvrquxl.thekolab.home.presenter

import com.google.android.material.appbar.AppBarLayout
import fr.lvmvrquxl.thekolab.home.view.HomeView

/**
 * Listener's implementation of the home page's toolbar.
 *
 * @param view View of the home page
 *
 * @since 0.1.3
 * @see [HomeView]
 */
class HomeToolbarListenerImpl(private val view: HomeView) : HomeToolbarListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_MAX: Int = 589
        private const val OFFSET_LIMIT: Int = (OFFSET_MAX * 5) / 100
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBar)
        if (OFFSET_LIMIT >= this.scrollRange + verticalOffset)
            this.view.showCollapsingToolbarTitle()
        else this.view.hideCollapsingToolbarTitle()
    }

    private fun initScrollRange(appBar: AppBarLayout) {
        this.scrollRange = appBar.totalScrollRange
    }
}