package fr.lvmvrquxl.thekolab.home.toolbar

import com.google.android.material.appbar.AppBarLayout

/**
 * Listener's interface of the home page's toolbar.
 *
 * This listener is dedicated for managing interactions with the toolbar, like an offset
 * changing.
 *
 * @since 0.1.3
 */
internal interface ToolbarListener : AppBarLayout.OnOffsetChangedListener {
    companion object {
        /**
         * Create a new toolbar's listener.
         *
         * This method creates a new listener for the home page's toolbar.
         *
         * @param callback Callback with hide and show toolbar's title
         * @return The new toolbar's listener
         *
         * @since 0.1.3
         * @see [ToolbarCallback]
         */
        fun create(callback: ToolbarCallback): ToolbarListener = ToolbarListenerImpl(callback)
    }
}