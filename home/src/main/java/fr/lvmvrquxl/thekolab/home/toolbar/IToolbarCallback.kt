package fr.lvmvrquxl.thekolab.home.toolbar

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding

/**
 * Callback of toolbar's interactions.
 *
 * This interface should be used to interact between the home page's view and
 * the toolbar's listener.
 *
 * @since 1.0.0
 */
internal interface IToolbarCallback {
    companion object {
        /**
         * Create an instance of the toolbar's interactions callback.
         *
         * @param activity Home page's activity
         * @param toolbar Toolbar's view binding
         *
         * @return New instance of the toolbar's interactions callback
         *
         * @since 1.0.0
         *
         * @see [AppCompatActivity]
         * @see [ToolbarBinding]
         */
        fun create(activity: AppCompatActivity, toolbar: ToolbarBinding): IToolbarCallback =
            ToolbarCallback.create(activity, toolbar)
    }

    /**
     * Hide the tab indicators of the toolbar's view pager.
     *
     * Consider using the [showTabIndicators] method for showing the tab indicators.
     *
     * @since 1.0.0
     *
     * @see [showTabIndicators]
     */
    fun hideTabIndicators()

    /**
     * Hide the toolbar's content.
     *
     * Consider using the [showToolbarContent] method for showing the toolbar's content.
     *
     * @since 1.0.0
     *
     * @see [showToolbarContent]
     */
    fun hideToolbarContent()

    /**
     * Show the tab indicators of the toolbar's view pager.
     *
     * Consider using the [hideTabIndicators] method for hiding the tab indicators.
     *
     * @since 1.0.0
     *
     * @see [hideTabIndicators]
     */
    fun showTabIndicators()

    /**
     * Show the toolbar's content.
     *
     * Consider using the [hideToolbarContent] method for hiding the toolbar's content.
     *
     * @since 1.0.0
     *
     * @see [hideToolbarContent]
     */
    fun showToolbarContent()
}