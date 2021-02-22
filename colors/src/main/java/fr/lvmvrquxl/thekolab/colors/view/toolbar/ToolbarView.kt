package fr.lvmvrquxl.thekolab.colors.view.toolbar

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.shared.view.ContainerView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * View of the toolbar's container.
 *
 * @param activity Instance of the colors activity
 * @param toolbar Binding of the view
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 * @see ContainerView
 */
internal class ToolbarView private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ColorsToolbarBinding
) : ContainerView() {
    companion object {
        /**
         * Create an instance of the toolbar's container.
         *
         * @param activity Instance of the colors activity
         * @param toolbar Binding of the view
         *
         * @return New instance of the container
         *
         * @since 1.0.0
         *
         * @see AppCompatActivity
         * @see LifecycleView
         */
        fun create(activity: AppCompatActivity, toolbar: ColorsToolbarBinding): LifecycleView =
            ToolbarView(activity, toolbar)
    }

    override fun onCreate() {
        this.registerExitView()
        this.registerTitleView()
        super.onCreate()
    }

    private fun registerExitView() {
        val view: LifecycleView = ExitView.create(this.activity, this.toolbar.exit)
        super.addView(view)
    }

    private fun registerTitleView() {
        val view: LifecycleView = TitleView.create(this.activity, this.toolbar.title)
        super.addView(view)
    }
}