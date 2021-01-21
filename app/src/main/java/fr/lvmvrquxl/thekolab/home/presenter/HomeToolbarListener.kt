package fr.lvmvrquxl.thekolab.home.presenter

import com.google.android.material.appbar.AppBarLayout
import fr.lvmvrquxl.thekolab.home.view.HomeView

interface HomeToolbarListener : AppBarLayout.OnOffsetChangedListener {
    companion object {
        fun build(view: HomeView): HomeToolbarListener = HomeToolbarListenerImpl(view)
    }
}