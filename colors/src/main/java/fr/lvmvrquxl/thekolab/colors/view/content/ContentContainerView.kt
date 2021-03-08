package fr.lvmvrquxl.thekolab.colors.view.content

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ContainerView

/**
 * View of the layout responsible for displaying the colors activity's content.
 *
 * @since 2.0.0
 */
internal class ContentContainerView private constructor(
    private val activity: Activity,
    private val binding: ColorsContentBinding
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
        fun observe(activity: Activity, binding: ColorsContentBinding) =
            ContentContainerView(activity, binding).let { view: ContentContainerView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerChangeColorsView()
        this.registerColorInfoView()
    }

    private fun registerChangeColorsView() =
        ChangeColorsView.observe(this.activity, this.binding.changeColors)

    private fun registerColorInfoView() =
        ColorInfoView.observe(this.activity, this.binding.colorInfo)

    private fun stopActivityObservation() = this.activity.removeObserver(this)
}