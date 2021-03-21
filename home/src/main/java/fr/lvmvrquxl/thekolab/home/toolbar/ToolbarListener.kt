package fr.lvmvrquxl.thekolab.home.toolbar

import com.google.android.material.appbar.AppBarLayout

/**
 * Listener's implementation of the home page's toolbar.
 *
 * This class should not be used directly! Use [IToolbarListener] instead.
 *
 * @param callback Toolbar's callback
 *
 * @since 1.0.0
 *
 * @see [IToolbarCallback]
 * @see [IToolbarListener]
 */
@Deprecated("Should be refactored for version 2.1.0")
internal class ToolbarListener private constructor(private val callback: IToolbarCallback) :
    IToolbarListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_MAX: Int = 589
        private const val TAB_INDICATORS_OFFSET_LIMIT: Int = (OFFSET_MAX * 50) / 100
        private const val TITLE_OFFSET_LIMIT: Int = (OFFSET_MAX * 5) / 100

        /**
         * Create a new instance of listener's implementation.
         *
         * @param callback Toolbar's callback
         *
         * @return New instance of listener's implementation
         *
         * @since 1.0.0
         *
         * @see [IToolbarCallback]
         * @see [IToolbarListener]
         */
        fun create(callback: IToolbarCallback): IToolbarListener = ToolbarListener(callback)
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBar)
        if (TITLE_OFFSET_LIMIT >= this.scrollRange + verticalOffset) this.callback.showToolbarContent()
        else this.callback.hideToolbarContent()
        if (TAB_INDICATORS_OFFSET_LIMIT >= this.scrollRange + verticalOffset)
            this.callback.hideTabIndicators()
        else this.callback.showTabIndicators()
    }

    private fun initScrollRange(appBar: AppBarLayout) {
        this.scrollRange = appBar.totalScrollRange
    }
}