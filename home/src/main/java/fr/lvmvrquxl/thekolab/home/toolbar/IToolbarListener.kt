package fr.lvmvrquxl.thekolab.home.toolbar

import com.google.android.material.appbar.AppBarLayout

/**
 * Listener's of the home page's toolbar.
 *
 * This interface is responsible for managing interactions with the collapsing toolbar.
 *
 * @since 0.1.3
 *
 * @see [AppBarLayout.OnOffsetChangedListener]
 */
internal interface IToolbarListener : AppBarLayout.OnOffsetChangedListener {
    companion object {
        /**
         * Create a new instance of toolbar's listener.
         *
         * @param callback Toolbar's callback
         *
         * @return The new instance of toolbar's listener
         *
         * @since 0.1.3
         *
         * @see [IToolbarCallback]
         */
        fun create(callback: IToolbarCallback): IToolbarListener = ToolbarListener.create(callback)
    }
}