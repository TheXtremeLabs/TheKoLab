package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.view.content.ContentView
import fr.lvmvrquxl.thekolab.colors.view.toolbar.ToolbarView
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * Main view of the colors activity.
 *
 * @param activity Instance of the colors activity
 *
 * @since 1.0.0
 *
 * @see ActivityView
 * @see AppCompatActivity
 */
internal class ColorsView private constructor(private val activity: AppCompatActivity) :
    ActivityView<ColorsActivityBinding>() {
    companion object {
        /**
         * Create an instance of the view.
         *
         * @param activity Instance of the colors activity
         *
         * @return New instance of the view
         *
         * @since 1.0.0
         *
         * @see ActivityView
         * @see AppCompatActivity
         */
        fun create(activity: AppCompatActivity): ActivityView<ColorsActivityBinding> =
            ColorsView(activity)
    }

    override fun onCreate() {
        this.bindView()
        this.registerContentView()
        this.registerToolbarView()
        super.onCreate()
    }

    private fun bindView() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }

    private fun registerContentView() =
        super.viewBinding?.colorsContent?.let { content: ColorsContentBinding ->
            val view: LifecycleView = ContentView.create(this.activity, content)
            super.views.add(view)
        }

    private fun registerToolbarView() =
        super.viewBinding?.colorsToolbar?.let { toolbar: ColorsToolbarBinding ->
            val view: LifecycleView = ToolbarView.create(this.activity, toolbar)
            super.views.add(view)
        }
}