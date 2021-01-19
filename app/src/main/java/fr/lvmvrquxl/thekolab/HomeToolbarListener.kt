package fr.lvmvrquxl.thekolab

import android.content.Context
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class HomeToolbarListener(
    private val context: Context,
    private val collapsingToolbar: CollapsingToolbarLayout
) : AppBarLayout.OnOffsetChangedListener {
    private var scrollRange: Int = -1
    private var showTitle: Boolean = false

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (-1 == this.scrollRange) this.scrollRange = appBarLayout.totalScrollRange
        if (0 == this.scrollRange + verticalOffset) {
            this.putAppNameAsTitle()
            this.updateShowTitle()
        } else if (this.showTitle) {
            this.resetTitle()
            this.updateShowTitle()
        }
    }

    private fun putAppNameAsTitle() {
        this.collapsingToolbar.title = Strings.appName(context)
    }

    private fun resetTitle() {
        this.collapsingToolbar.title = ""
    }

    private fun updateShowTitle() {
        this.showTitle = !this.showTitle
    }
}