package fr.lvmvrquxl.thekolab.colors.view.content

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LayoutView

/**
 * View of the layout responsible for displaying the colors activity's content.
 *
 * @since 2.0.0
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
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference, binding: ColorsContentBinding) {
            ContentLayoutView(activityReference, binding).let { view: ContentLayoutView ->
                activityReference.get()?.addObserver(view)
            }
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