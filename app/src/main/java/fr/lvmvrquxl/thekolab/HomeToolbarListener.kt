package fr.lvmvrquxl.thekolab

import com.google.android.material.appbar.AppBarLayout

class HomeToolbarListener(private val activity: HomeActivity) :
    AppBarLayout.OnOffsetChangedListener {
    private var scrollRange: Int = -1
    private var showTitle: Boolean = false

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (-1 == this.scrollRange) this.initScrollRange(appBarLayout)
        if (0 == this.scrollRange + verticalOffset) {
            this.activity.showCollapsingToolbarTitle()
            this.updateShowTitle()
        } else if (this.showTitle) {
            this.activity.hideCollapsingToolbarTitle()
            this.updateShowTitle()
        }
    }

    private fun initScrollRange(appBarLayout: AppBarLayout) {
        this.scrollRange = appBarLayout.totalScrollRange
    }

    private fun updateShowTitle() {
        this.showTitle = !this.showTitle
    }
}