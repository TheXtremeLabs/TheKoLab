package fr.lvmvrquxl.thekolab.colors.view.toolbar

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ContainerView

/**
 * View of the layout responsible for displaying the colors activity's toolbar.
 *
 * @since 2.0.0
 */
internal class ToolbarContainerView private constructor(
    private val activity: Activity,
    private val binding: ColorsToolbarBinding
) : ContainerView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Colors activity
         * @param binding Binding of the view
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity, binding: ColorsToolbarBinding) =
            ToolbarContainerView(activity, binding).let { view: ToolbarContainerView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() = this.activity.removeObserver(this)

    override fun registerViews() {
        this.registerExitView()
        this.registerTitleView()
    }

    private fun registerExitView() = ExitView.observe(this.activity, this.binding.exit)

    private fun registerTitleView() = TitleView.observe(this.activity, this.binding.title)
}