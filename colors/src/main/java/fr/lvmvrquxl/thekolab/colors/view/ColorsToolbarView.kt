package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorsToolbarView private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ColorsToolbarBinding
) : LifecycleView {
    companion object {
        fun create(activity: AppCompatActivity, toolbar: ColorsToolbarBinding): LifecycleView =
            ColorsToolbarView(activity, toolbar)
    }

    private val views: MutableList<LifecycleView> = mutableListOf()

    override fun onCreate() {
        this.registerExit()
        this.registerTitle()
        this.views.forEach { view: LifecycleView -> view.onCreate() }
    }

    override fun onDestroy() = this.views.clear()

    override fun onStart() = this.views.forEach { view: LifecycleView -> view.onStart() }

    private fun registerExit() {
        val exit: LifecycleView = ExitView.create(this.activity, this.toolbar.exit)
        this.views.add(exit)
    }

    private fun registerTitle() {
        val title: LifecycleView = TitleView.create(this.activity, this.toolbar.title)
        this.views.add(title)
    }
}