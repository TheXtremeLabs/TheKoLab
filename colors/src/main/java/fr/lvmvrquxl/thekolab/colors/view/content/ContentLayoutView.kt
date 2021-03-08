package fr.lvmvrquxl.thekolab.colors.view.content

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.LayoutView

/**
 * View of the layout responsible for displaying the colors activity's content.
 *
 * @since 2.0.0
 */
internal class ContentLayoutView private constructor(
    private val activity: Activity,
    private val binding: ColorsContentBinding
) : LayoutView() {
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
            ContentLayoutView(activity, binding).let { view: ContentLayoutView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() = this.activity.removeObserver(this)

    override fun registerViews() {
        this.registerChangeColorsView()
        this.registerColorInfoView()
    }

    private fun registerChangeColorsView() =
        ChangeColorsView.observe(this.activity, this.binding.changeColors)

    private fun registerColorInfoView() =
        ColorInfoView.observe(this.activity, this.binding.colorInfo)
}