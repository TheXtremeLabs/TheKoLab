package fr.lvmvrquxl.thekolab.home.toolbar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils

/**
 * Implementation of toolbar's interactions.
 *
 * This class should not be used directly! Use [IToolbarCallback] instead.
 *
 * @param activity Home page's activity
 * @param toolbar Toolbar's view binding
 *
 * @since 1.0.0
 *
 * @see [AppCompatActivity]
 * @see [IToolbarCallback]
 */
internal class ToolbarCallback private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ToolbarBinding
) : IToolbarCallback {
    companion object {
        /**
         * Create a new instance of toolbar's callback implementation.
         *
         * @param activity Home page's activity
         * @param toolbar Toolbar's view binding
         *
         * @return New instance of toolbar's callback implementation
         *
         * @since 1.0.0
         *
         * @see [AppCompatActivity]
         * @see [IToolbarCallback]
         * @see [ToolbarBinding]
         */
        fun create(activity: AppCompatActivity, toolbar: ToolbarBinding): IToolbarCallback =
            ToolbarCallback(activity, toolbar)
    }

    private val animationDuration: Int =
        this.activity.resources.getInteger(android.R.integer.config_shortAnimTime)

    override fun hideTabIndicators() {
        this.toolbar.tabIndicators.apply {
            this.alpha = 0f
            this.visibility = View.GONE
        }
    }

    override fun hideTitle() {
        this.toolbar.collapsingToolbar.title = " "
    }

    override fun showTabIndicators() {
        this.toolbar.tabIndicators.apply {
            this.animate()
                .alpha(0.75f)
                .setDuration(animationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        this@apply.visibility = View.VISIBLE
                    }
                })
        }
    }

    override fun showTitle() {
        this.toolbar.collapsingToolbar.title = SharedStringUtils.appName(this.activity)
    }
}