package fr.lvmvrquxl.thekolab.colors.view.toolbar

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LayoutView

/**
 * View of the layout responsible for displaying the colors activity's toolbar.
 */
internal class ToolbarLayoutView private constructor(
    private val activityReference: ActivityReference,
    private val binding: ColorsToolbarBinding
) : LayoutView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the colors activity
         * @param binding Binding of the view
         */
        fun observe(activityReference: ActivityReference, binding: ColorsToolbarBinding) {
            val toolbarLayoutView = ToolbarLayoutView(activityReference, binding)
            activityReference.get()?.addObserver(toolbarLayoutView)
        }
    }

    override fun onDestroy() {
        this.activityReference.get()?.removeObserver(this)
    }

    override fun registerViews() {
        this.registerExitView()
        this.registerTitleView()
    }

    private fun registerExitView() = ExitView.observe(this.activityReference, this.binding.exit)

    private fun registerTitleView() = TitleView.observe(this.activityReference, this.binding.title)
}