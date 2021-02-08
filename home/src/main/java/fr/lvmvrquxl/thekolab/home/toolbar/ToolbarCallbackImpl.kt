package fr.lvmvrquxl.thekolab.home.toolbar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarBinding
import fr.lvmvrquxl.thekolab.shared.utils.StringUtils

internal class ToolbarCallbackImpl(
    private val activity: AppCompatActivity,
    private val toolbar: ToolbarBinding
) : ToolbarCallback {
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
        this.toolbar.collapsingToolbar.title = StringUtils.appName(this.activity)
    }
}