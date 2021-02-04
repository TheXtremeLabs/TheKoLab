package fr.lvmvrquxl.thekolab.home.toolbar

import com.google.android.material.appbar.AppBarLayout

/**
 * Listener's implementation of the home page's toolbar.
 *
 * @param callback Callback with hide and show title methods
 *
 * @since 0.1.3
 * @see [HomeToolbarCallback]
 */
internal class HomeToolbarListenerImpl(private val callback: HomeToolbarCallback) :
    HomeToolbarListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_MAX: Int = 589
        private const val OFFSET_LIMIT: Int = (OFFSET_MAX * 5) / 100
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBar)
        if (OFFSET_LIMIT >= this.scrollRange + verticalOffset) this.callback.showTitle()
        else this.callback.hideTitle()
    }

    private fun initScrollRange(appBar: AppBarLayout) {
        this.scrollRange = appBar.totalScrollRange
    }
}