package fr.lvmvrquxl.thekolab.colors.view

import android.app.Activity
import android.view.View
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.presenter.ColorsPresenter
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsView private constructor(private val activity: Activity) :
    ActivityView<ColorsActivityBinding>() {
    companion object {
        fun create(activity: Activity): ActivityView<ColorsActivityBinding> = ColorsView(activity)
    }

    private var presenter: ColorsPresenter? = null
    private var content: ColorsContentBinding? = null
    private var toolbar: ColorsToolbarBinding? = null

    override fun onCreate() {
        this.bindViews()
        this.presenter = ColorsPresenter.create(this.activity)
    }

    override fun onDestroy() {
        this.presenter = null
        super.onDestroy()
    }

    override fun onStart() {
        this.initColor()
        this.setBackArrowListener()
        this.setChangeColorsListener()
    }

    private fun bindContent() {
        this.content = super.viewBinding?.colorsContent
    }

    private fun bindToolbar() {
        this.toolbar = super.viewBinding?.colorsToolbar
    }

    private fun bindViews() {
        this.inflateViewBinding()
        this.bindContent()
        this.bindToolbar()
    }

    private fun inflateViewBinding() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }

    private fun initChangeColors(color: Int) = this.content?.let { content: ColorsContentBinding ->
        content.changeColors.setBackgroundColor(color)
    }

    private fun initColor() = this.presenter?.let { presenter: ColorsPresenter ->
        val color: Int = presenter.currentColor()
        val name: String = presenter.currentColorName()
        this.initColorName(name, color)
        this.initChangeColors(color)
    }

    private fun initColorName(name: String, color: Int) =
        this.content?.let { content: ColorsContentBinding ->
            content.colorName.apply {
                this.text = name
                this.setTextColor(color)
            }
        }

    private fun setBackArrowListener() = this.toolbar?.let { toolbar: ColorsToolbarBinding ->
        toolbar.backArrow.setOnClickListener { this.activity.onBackPressed() }
    }

    private fun setChangeColorsListener() = this.content?.let { content: ColorsContentBinding ->
        this.presenter?.let { p: ColorsPresenter ->
            super.viewBinding?.let { binding: ColorsActivityBinding ->
                val listener: View.OnClickListener =
                    ColorsClickListener.create(p, binding, this.activity)
                content.changeColors.setOnClickListener(listener)
            }
        }
    }
}