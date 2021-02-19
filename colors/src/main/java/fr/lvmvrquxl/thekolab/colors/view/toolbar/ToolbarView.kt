package fr.lvmvrquxl.thekolab.colors.view.toolbar

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.shared.view.ContainerView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ToolbarView private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ColorsToolbarBinding
) : ContainerView() {
    companion object {
        fun create(activity: AppCompatActivity, toolbar: ColorsToolbarBinding): LifecycleView =
            ToolbarView(activity, toolbar)
    }

    override fun onCreate() {
        this.registerExit()
        this.registerTitle()
        super.onCreate()
    }

    private fun registerExit() {
        val exit: LifecycleView = ExitView.create(this.activity, this.toolbar.exit)
        super.views.add(exit)
    }

    private fun registerTitle() {
        val title: LifecycleView = TitleView.create(this.activity, this.toolbar.title)
        super.views.add(title)
    }
}