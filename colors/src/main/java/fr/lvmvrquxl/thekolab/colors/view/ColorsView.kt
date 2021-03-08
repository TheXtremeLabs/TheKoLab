package fr.lvmvrquxl.thekolab.colors.view

import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.view.content.ContentContainerView
import fr.lvmvrquxl.thekolab.colors.view.toolbar.ToolbarContainerView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Main view of the colors activity.
 *
 * @since 1.0.0
 */
internal class ColorsView private constructor(private val activity: Activity) :
    ActivityView<ColorsActivityBinding>(activity) {
    companion object {
        /**
         * Observe the given activity lifecycle.
         *
         * @param activity Colors activity
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity) =
            ColorsView(activity).let { view: ColorsView -> activity.addObserver(view) }
    }

    private var contentBinding: ColorsContentBinding? = null
    private var toolbarBinding: ColorsToolbarBinding? = null

    override fun bindView() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater).apply {
            this@ColorsView.contentBinding = this.colorsContent
            this@ColorsView.toolbarBinding = this.colorsToolbar
        }
    }

    override fun onDestroy() {
        this.destroyContentBinding()
        this.destroyToolbarBinding()
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerContainerView()
        this.registerToolbarView()
    }

    private fun destroyContentBinding() {
        this.contentBinding = null
    }

    private fun destroyToolbarBinding() {
        this.toolbarBinding = null
    }

    private fun registerContainerView() =
        this.contentBinding?.let { binding: ColorsContentBinding ->
            ContentContainerView.observe(this.activity, binding)
        }

    private fun registerToolbarView() = this.toolbarBinding?.let { binding: ColorsToolbarBinding ->
        ToolbarContainerView.observe(this.activity, binding)
    }

    private fun stopActivityObservation() = this.activity.removeObserver(this)
}