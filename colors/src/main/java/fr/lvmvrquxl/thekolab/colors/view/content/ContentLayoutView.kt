package fr.lvmvrquxl.thekolab.colors.view.content

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LayoutView

/**
 * View of the layout responsible for displaying the colors activity's content.
 */
internal class ContentLayoutView private constructor(
    private val activityReference: ActivityReference,
    private val binding: ColorsContentBinding
) : LayoutView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Colors activity's reference
         * @param binding Binding of the view
         */
        fun observe(activityReference: ActivityReference, binding: ColorsContentBinding) {
            val contentLayoutView = ContentLayoutView(activityReference, binding)
            activityReference.get()?.addObserver(contentLayoutView)
        }
    }

    override fun onDestroy() {
        this.activityReference.get()?.removeObserver(this)
    }

    override fun registerViews() {
        this.registerChangeColorsView()
        this.registerColorInfoView()
    }

    private fun registerChangeColorsView() =
        ChangeColorsView.observe(this.activityReference, this.binding.changeColors)

    private fun registerColorInfoView() =
        ColorInfoView.observe(this.activityReference, this.binding.colorInfo)
}