package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorsView private constructor(private val activity: AppCompatActivity) :
    ActivityView<ColorsActivityBinding>() {
    companion object {
        fun create(activity: AppCompatActivity): ActivityView<ColorsActivityBinding> =
            ColorsView(activity)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private val views: MutableList<LifecycleView> = mutableListOf()

    override fun onCreate() {
        this.bindViews()
        this.views.forEach { view: LifecycleView -> view.onCreate() }
    }

    override fun onDestroy() {
        this.viewModel.onDestroy()
        this.views.forEach { view: LifecycleView -> view.onDestroy() }
        this.views.clear()
        super.onDestroy()
    }

    override fun onStart() {
        this.views.forEach { view: LifecycleView -> view.onStart() }
        this.viewModel.onStart()
    }

    private fun bindContent() =
        super.viewBinding?.colorsContent?.let { content: ColorsContentBinding ->
            val view: LifecycleView = ColorsContentView.create(this.activity, content)
            this.views.add(view)
        }

    private fun bindToolbar() =
        super.viewBinding?.colorsToolbar?.let { toolbar: ColorsToolbarBinding ->
            val view: LifecycleView = ColorsToolbarView.create(this.activity, toolbar)
            this.views.add(view)
        }

    private fun bindViews() {
        this.inflateViewBinding()
        this.bindContent()
        this.bindToolbar()
    }

    private fun inflateViewBinding() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }
}