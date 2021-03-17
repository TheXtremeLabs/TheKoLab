package fr.lvmvrquxl.thekolab.colors.view

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.view.content.ContentLayoutView
import fr.lvmvrquxl.thekolab.colors.view.toolbar.ToolbarLayoutView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Main view of the colors activity.
 *
 * @since 2.0.0
 */
internal class ColorsView private constructor(private val activityReference: ActivityReference) :
    ActivityView<ColorsActivityBinding>(activityReference) {
    companion object {
        /**
         * Observe the given activity lifecycle.
         *
         * @param activityReference Colors activity's reference
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference) {
            ColorsView(activityReference).let { view: ColorsView ->
                activityReference.get()?.addObserver(view)
            }
        }
    }

    private var contentBinding: ColorsContentBinding? = null
    private var toolbarBinding: ColorsToolbarBinding? = null

    override fun bindView() {
        this.activityReference.get()?.let { activity: Activity ->
            super.viewBinding = ColorsActivityBinding.inflate(activity.layoutInflater).apply {
                this@ColorsView.contentBinding = this.colorsContent
                this@ColorsView.toolbarBinding = this.colorsToolbar
            }
        }
    }

    override fun onDestroy() {
        this.destroyContentBinding()
        this.destroyToolbarBinding()
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerContainerLayout()
        this.registerToolbarLayout()
    }

    private fun destroyContentBinding() {
        this.contentBinding = null
    }

    private fun destroyToolbarBinding() {
        this.toolbarBinding = null
    }

    private fun registerContainerLayout() =
        this.contentBinding?.let { binding: ColorsContentBinding ->
            ContentLayoutView.observe(this.activityReference, binding)
        }

    private fun registerToolbarLayout() =
        this.toolbarBinding?.let { binding: ColorsToolbarBinding ->
            ToolbarLayoutView.observe(this.activityReference, binding)
        }

    private fun stopActivityObservation() = this.activityReference.get()?.removeObserver(this)
}