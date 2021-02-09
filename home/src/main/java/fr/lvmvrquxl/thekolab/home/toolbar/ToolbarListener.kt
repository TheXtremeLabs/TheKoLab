package fr.lvmvrquxl.thekolab.home.toolbar

import com.google.android.material.appbar.AppBarLayout

/**
 * Listener's implementation of the home page's toolbar.
 *
 * This class should not be used directly! Use [IToolbarListener] instead.
 *
 * @param callback Toolbar's callback
 *
 * @since 0.1.3
 *
 * @see [IToolbarCallback]
 * @see [IToolbarListener]
 */
internal class ToolbarListener(private val callback: IToolbarCallback) :
    IToolbarListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_MAX: Int = 589
        private const val TAB_INDICATORS_OFFSET_LIMIT: Int = (OFFSET_MAX * 50) / 100
        private const val TITLE_OFFSET_LIMIT: Int = (OFFSET_MAX * 5) / 100
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBar)
        if (TITLE_OFFSET_LIMIT >= this.scrollRange + verticalOffset) this.callback.showTitle()
        else this.callback.hideTitle()
        if (TAB_INDICATORS_OFFSET_LIMIT >= this.scrollRange + verticalOffset)
            this.callback.hideTabIndicators()
        else this.callback.showTabIndicators()
    }

    private fun initScrollRange(appBar: AppBarLayout) {
        this.scrollRange = appBar.totalScrollRange
    }
}