package fr.lvmvrquxl.thekolab.home.presenter

import com.google.android.material.appbar.AppBarLayout
import fr.lvmvrquxl.thekolab.home.view.HomeView

/**
 * Listener's interface of the home page's toolbar.
 *
 * This listener is dedicated for managing interactions with the toolbar, like an offset
 * changing.
 *
 * @since 0.1.3
 */
interface HomeToolbarListener : AppBarLayout.OnOffsetChangedListener {
    companion object {
        /**
         * Build a new toolbar's listener.
         *
         * This method creates a new listener for the home page's toolbar.
         *
         * @param view View of the home page
         * @return The new toolbar's listener
         *
         * @since 0.1.3
         * @see [HomeView]
         */
        fun build(view: HomeView): HomeToolbarListener = HomeToolbarListenerImpl(view)
    }
}