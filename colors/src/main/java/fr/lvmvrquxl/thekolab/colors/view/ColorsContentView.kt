package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorsContentView private constructor(
    private val activity: AppCompatActivity,
    private val content: ColorsContentBinding
) : LifecycleView {
    companion object {
        fun create(activity: AppCompatActivity, content: ColorsContentBinding): LifecycleView =
            ColorsContentView(activity, content)
    }

    private val views: MutableList<LifecycleView> = mutableListOf()

    override fun onCreate() {
        this.registerChangeColors()
        this.registerColorInfo()
        this.views.forEach { view: LifecycleView -> view.onCreate() }
    }

    override fun onDestroy() {
        this.views.forEach { view: LifecycleView -> view.onDestroy() }
        this.views.clear()
    }

    override fun onStart() = this.views.forEach { view: LifecycleView -> view.onStart() }

    private fun registerChangeColors() {
        val changeColors: LifecycleView =
            ChangeColorsView.create(this.activity, this.content.changeColors)
        this.views.add(changeColors)
    }

    private fun registerColorInfo() {
        val colorInfo: LifecycleView = ColorInfoView.create(this.activity, this.content.colorInfo)
        this.views.add(colorInfo)
    }
}