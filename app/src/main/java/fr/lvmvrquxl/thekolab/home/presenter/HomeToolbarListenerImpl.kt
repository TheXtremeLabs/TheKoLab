package fr.lvmvrquxl.thekolab.home.presenter

import com.google.android.material.appbar.AppBarLayout
import fr.lvmvrquxl.thekolab.home.view.HomeView

class HomeToolbarListenerImpl(private val view: HomeView) : HomeToolbarListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_LIMIT: Int = 0
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBar)
        if (OFFSET_LIMIT == this.scrollRange + verticalOffset)
            this.view.showCollapsingToolbarTitle()
        else this.view.hideCollapsingToolbarTitle()
    }

    private fun initScrollRange(appBar: AppBarLayout) {
        this.scrollRange = appBar.totalScrollRange
    }
}