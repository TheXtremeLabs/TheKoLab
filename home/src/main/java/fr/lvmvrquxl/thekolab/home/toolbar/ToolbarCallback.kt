package fr.lvmvrquxl.thekolab.home.toolbar

import android.view.View
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Implementation of toolbar's interactions.
 *
 * This class should not be used directly! Use [IToolbarCallback] instead.
 *
 * @since 2.0.0
 */
internal class ToolbarCallback private constructor(
    private val activityReference: ActivityReference,
    private val toolbar: ToolbarBinding
) : IToolbarCallback {
    companion object {
        /**
         * Create a new instance of toolbar's callback implementation.
         *
         * @param activityReference Reference of the home page's activity
         * @param toolbar Toolbar's view binding
         *
         * @return New instance of toolbar's callback implementation
         *
         * @since 2.0.0
         */
        fun create(
            activityReference: ActivityReference,
            toolbar: ToolbarBinding
        ): IToolbarCallback = ToolbarCallback(activityReference, toolbar)
    }

    private val animationDuration: Int?
        get() = this.activityReference.get()?.let { activity: Activity ->
            activity.resources.getInteger(android.R.integer.config_shortAnimTime)
        }

    override fun hideTabIndicators() {
        this.toolbar.tabIndicators.apply {
            this.alpha = 0f
            this.visibility = View.GONE
        }
    }

    override fun hideToolbarContent() {
        this.toolbar.toolbarContent.apply {
            this.alpha = 0f
            this.visibility = View.GONE
        }
    }

    override fun showTabIndicators() {
        this.animationDuration?.let { duration: Int ->
            this.toolbar.tabIndicators.animate()
                .alpha(0.75f)
                .setDuration(duration.toLong())
                .withStartAction { this.toolbar.tabIndicators.visibility = View.VISIBLE }
        }
    }

    override fun showToolbarContent() {
        this.animationDuration?.let { duration: Int ->
            this.toolbar.toolbarContent.animate()
                .alpha(0.75f)
                .setDuration(duration.toLong())
                .withStartAction { this.toolbar.toolbarContent.visibility = View.VISIBLE }
        }
    }
}