package fr.lvmvrquxl.thekolab.colors.view.content

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.view.ContainerView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * View of the content's container.
 *
 * @param activity Instance of the colors activity
 * @param content Binding of the view
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 * @see ContainerView
 */
internal class ContentView private constructor(
    private val activity: AppCompatActivity,
    private val content: ColorsContentBinding
) : ContainerView() {
    companion object {
        /**
         * Create an instance of the content's container.
         *
         * @param activity Instance of the colors activity
         * @param content Binding of the view
         *
         * @return New instance of the view
         *
         * @since 1.0.0
         *
         * @see AppCompatActivity
         * @see LifecycleView
         */
        fun create(activity: AppCompatActivity, content: ColorsContentBinding): LifecycleView =
            ContentView(activity, content)
    }

    override fun onCreate() {
        this.registerChangeColors()
        this.registerColorInfo()
        super.onCreate()
    }

    private fun registerChangeColors() {
        val changeColors: LifecycleView =
            ChangeColorsView.create(this.activity, this.content.changeColors)
        super.views.add(changeColors)
    }

    private fun registerColorInfo() {
        val colorInfo: LifecycleView = ColorInfoView.create(this.activity, this.content.colorInfo)
        super.views.add(colorInfo)
    }
}