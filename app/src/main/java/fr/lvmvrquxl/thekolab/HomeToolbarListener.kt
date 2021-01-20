package fr.lvmvrquxl.thekolab

import com.google.android.material.appbar.AppBarLayout

class HomeToolbarListener(private val activity: HomeActivity) :
    AppBarLayout.OnOffsetChangedListener {
    companion object {
        private const val INITIAL_SCROLL_RANGE: Int = -1
        private const val OFFSET_LIMIT: Int = 0
    }

    private var scrollRange: Int = INITIAL_SCROLL_RANGE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (INITIAL_SCROLL_RANGE == this.scrollRange) this.initScrollRange(appBarLayout)
        if (OFFSET_LIMIT == this.scrollRange + verticalOffset)
            this.activity.showCollapsingToolbarTitle()
        else this.activity.hideCollapsingToolbarTitle()
    }

    private fun initScrollRange(appBarLayout: AppBarLayout) {
        this.scrollRange = appBarLayout.totalScrollRange
    }
}