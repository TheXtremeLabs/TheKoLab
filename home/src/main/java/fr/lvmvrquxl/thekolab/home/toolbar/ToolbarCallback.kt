package fr.lvmvrquxl.thekolab.home.toolbar

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding

internal interface ToolbarCallback {
    companion object {
        fun create(activity: AppCompatActivity, toolbar: ToolbarBinding): ToolbarCallback =
            ToolbarCallbackImpl(activity, toolbar)
    }

    fun hideTabIndicators()

    /**
     * Hide the collapsing toolbar's title.
     *
     * This method hides the collapsing toolbar's title by setting its value to `" "`.
     * Consider using the [showTitle] method for showing the collapsing toolbar's title.
     *
     * @since 0.1.3
     * @see [showTitle]
     */
    fun hideTitle()

    fun showTabIndicators()

    /**
     * Show the collapsing toolbar's title.
     *
     * This method shows the collapsing toolbar's title, which is the application's name.
     * Consider using the [hideTitle] method for hiding the collapsing toolbar's title.
     *
     * @since 0.1.3
     * @see [hideTitle]
     */
    fun showTitle()
}